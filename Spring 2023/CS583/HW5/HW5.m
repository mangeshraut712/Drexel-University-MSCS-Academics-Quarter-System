clc;
clear;
close all;

% Classifying an Image using Grayscale Histograms

% Step 1: Parsing the dataset and generating HOG feature vectors

d = './CarData/TrainImages';
car_filename_prefix = 'pos-';
non_car_filename_prefix = 'neg-';

% Check if the directory exists
if ~isfolder(d)
    error('Directory does not exist: %s', d);
end

% Load the original images
car_images = dir(fullfile(d, [car_filename_prefix '*.pgm']));
not_car_images = dir(fullfile(d, [non_car_filename_prefix '*.pgm']));
files = dir(d);
X = [];
Y = [];

for f = files'
    if ~f.isdir && ~strcmp(f.name, '.') && ~strcmp(f.name, '..')
        im = imread(fullfile(d, f.name));
        
        % Convert image to grayscale
        imgray = im2gray(im);

        % Compute grayscale histogram with 256 bins
        histogram = zeros(1, 256);
        [height, width] = size(imgray);

        for y = 1:height
            for x = 1:width
                intensity = imgray(y, x);
                histogram(intensity + 1) = histogram(intensity + 1) + 1;
            end
        end

        X = [X; histogram];

        % Extract class label from the first three characters of the file name
        if strcmp(f.name(1:3), 'neg')
            Y = [Y; 0]; % not a car
        else
            Y = [Y; 1]; % car
        end
    end
end

% Save the feature vectors and labels to a file for future use
save('feature_vectors.mat', 'X');
save('labels.mat', 'Y');

% Step 2: Shuffling and dividing data into training and validation subsets
rng(0); % Set the random number generator's seed for reproducibility
inds = randperm(size(X, 1));
num = size(X, 1) / 3;
X = X(inds, :);
Y = Y(inds, :);
Xtrain = X(1:2*num, :);
Ytrain = Y(1:2*num, :);
Xvalid = X(2*num+1:end, :);
Yvalid = Y(2*num+1:end, :);

% Step 3: Classifying validation samples using k-nearest neighbors
k = 5;
numValid = size(Xvalid, 1);
predictions = zeros(numValid, 1);

for i = 1:numValid
    histValid = Xvalid(i, :);
    histTrain = Xtrain;
    numTrain = size(histTrain, 1);
    similarities = zeros(numTrain, 1);

    % Compute similarity metric using histogram intersection
    for j = 1:numTrain
        histTrainRow = histTrain(j, :);
        similarities(j) = sum(min(histTrainRow, histValid));
    end

    % Find the k nearest neighbors
    [~, idx] = maxk(similarities, k);
    kNN = Ytrain(idx);

    % Assign the majority class label
    predictions(i) = mode(kNN);
end

% Compute accuracy
accuracy = sum(predictions == Yvalid) / numValid * 100;
fprintf('Accuracy: %.2f%%\n', accuracy);

% Report Examples
car_indices = find(Yvalid == 1);
not_car_indices = find(Yvalid == 0);

correct_car_index = car_indices(find(predictions(car_indices) == 1, 1));
correct_not_car_index = not_car_indices(find(predictions(not_car_indices) == 0, 1));
incorrect_car_index = car_indices(find(predictions(car_indices) == 0, 1));
incorrect_not_car_index = not_car_indices(find(predictions(not_car_indices) == 1, 1));

% Display the example images
figure;

subplot(2, 2, 1);
correct_car_im = imread(fullfile(d, car_images(correct_car_index).name));
imshow(correct_car_im);
title('Correctly Labeled Car');

subplot(2, 2, 2);
correct_not_car_im = imread(fullfile(d, not_car_images(correct_not_car_index).name));
imshow(correct_not_car_im);
title('Correctly Labeled Not a Car');

subplot(2, 2, 3);
incorrect_car_im = imread(fullfile(d, car_images(incorrect_car_index).name));
imshow(incorrect_car_im);
title('Incorrectly Labeled Car');

subplot(2, 2, 4);
incorrect_not_car_im = imread(fullfile(d, not_car_images(incorrect_not_car_index).name));
imshow(incorrect_not_car_im);
title('Incorrectly Labeled Not a Car');





% Classifying an Image using Gists

% Step 1: Parsing the dataset and generating HOG feature vectors

d = './CarData/TrainImages';
car_filename_prefix = 'pos-';
non_car_filename_prefix = 'neg-';

% Check if the directory exists
if ~isfolder(d)
    error('Directory does not exist: %s', d);
end

% Load the original images
car_images = dir(fullfile(d, [car_filename_prefix '*.pgm']));
not_car_images = dir(fullfile(d, [non_car_filename_prefix '*.pgm']));
files = dir(d);
X = [];
Y = [];

for f = files'
    if ~f.isdir && ~strcmp(f.name, '.') && ~strcmp(f.name, '..')
        im = imread(fullfile(d, f.name));

        % Convert image to grayscale
        imgray = im2gray(im);
        
        % Divide the image into 10 non-overlapping 20x20 sub-images
        sub_images = mat2cell(imgray, repmat(20, 1, size(imgray, 1)/20), repmat(20, 1, size(im, 2)/20));

        % Compute HOG features for each sub-image
        hog_features = [];
        for i = 1:numel(sub_images)
            hog = computeHOG(sub_images{i});
            hog_features = [hog_features, hog];
        end

        X = [X; hog_features];

        % Extract class label from the first three characters of the file name
        if strcmp(f.name(1:3), 'neg')
            Y = [Y; 0]; % not a car
        else
            Y = [Y; 1]; % car
        end
    end
end

% Step 2: Shuffling and dividing data into training and validation subsets
rng(0);
inds = randperm(size(X,1));
num = size(X,1)/3;
X = X(inds,:);
Y = Y(inds,:);
Xtrain = X(1:2*num,:);
Ytrain = Y(1:2*num,:);
Xvalid = X(2*num+1:end,:);
Yvalid = Y(2*num+1:end,:);

% Step 3: Classifying validation samples using k-nearest neighbors
k = 5;
numValid = size(Xvalid, 1);
predictions = zeros(numValid, 1);

for i = 1:numValid
    hogValid = Xvalid(i,:);
    hogTrain = Xtrain;

    % Compute similarity metric using histogram intersection (sum over 80 bins)
    similarities = sum(min(hogTrain, hogValid), 2);

    % Find the k nearest neighbors
    [~, idx] = maxk(similarities, k);
    kNN = Ytrain(idx);

    % Assign the majority class label
    predictions(i) = mode(kNN);
end

% Step 4: Compute accuracy and display sample images
accuracy = sum(predictions == Yvalid) / numValid * 100;
fprintf('Accuracy: %.2f%%\n', accuracy);

% Report Examples
car_indices = find(Yvalid == 1);
not_car_indices = find(Yvalid == 0);

correct_car_index = car_indices(find(predictions(car_indices) == 1, 1));
correct_not_car_index = not_car_indices(find(predictions(not_car_indices) == 0, 1));
incorrect_car_index = car_indices(find(predictions(car_indices) == 0, 1));
incorrect_not_car_index = not_car_indices(find(predictions(not_car_indices) == 1, 1));

% Display the example images
figure;
subplot(2, 2, 1);
correct_car_im = imread(fullfile(d, car_images(correct_car_index).name));
imshow(correct_car_im);
title('Correctly Labeled Car');

subplot(2, 2, 2);
correct_not_car_im = imread(fullfile(d, not_car_images(correct_not_car_index).name));
imshow(correct_not_car_im);
title('Correctly Labeled Not a Car');

subplot(2, 2, 3);
incorrect_car_im = imread(fullfile(d, car_images(incorrect_car_index).name));
imshow(incorrect_car_im);
title('Incorrectly Labeled Car');

subplot(2, 2, 4);
incorrect_not_car_im = imread(fullfile(d, not_car_images(incorrect_not_car_index).name));
imshow(incorrect_not_car_im);
title('Incorrectly Labeled Not a Car');

% Function to compute HOG features
function hog = computeHOG(im)
    % Gradient computation
    dx = [-1 0 1];
    dy = dx';
    Ix = conv2(im, dx, 'same');
    Iy = conv2(im, dy, 'same');
    % Magnitude and orientation computation
    magnitude = sqrt(Ix.^2 + Iy.^2);
    orientation = atan2(Iy, Ix);
    % Discretize orientations into 8 bins
    bins = -pi:pi/4:pi;
    hog = histcounts(orientation(:), bins);
end
