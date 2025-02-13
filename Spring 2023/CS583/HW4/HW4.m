% Nearest neighbor sampling.

function resizedImage = HW4(image, newWidth, newHeight)
% Read the image into memory.
h = size(image, 1);
w = size(image, 2);

% Create a new image with the desired size.
resizedImage = zeros(newHeight, newWidth, size(image, 3), class(image));

% For each pixel in the new image, find the corresponding pixel in the original image.
for i = 1:newHeight
    for j = 1:newWidth
        % Compute the corresponding pixel location in the original image.
        x = round(j * w / newWidth);
        y = round(i * h / newHeight);

        % Copy the pixel value from the original image to the new image.
        resizedImage(i, j, :) = image(y, x, :);
    end
end

% Display the original and resized images.
figure;
subplot(1, 2, 1);
imshow(image);
title('Original Image');
subplot(1, 2, 2);
imshow(resizedImage);
title('Nearest Neighbor Resized Image');

end

% This will gives total 4 images
% HW4(image1, 550, 350)
% HW4(image2, 550, 350)





% Bi-linear interpolation.

function resizedImage = HW4(image, newWidth, newHeight)
% Read the image into memory.
h = size(image, 1);
w = size(image, 2);

% Create a new image with the desired size.
resizedImage = zeros(newHeight, newWidth, size(image, 3), class(image));

% For each pixel in the new image, find the corresponding pixel values in the original image.
for i = 1:newHeight
    for j = 1:newWidth
        % Compute the corresponding pixel location in the original image.
        x = j * w / newWidth;
        y = i * h / newHeight;

        % Compute the four nearest pixel locations in the original image.
        x1 = floor(x);
        x2 = ceil(x);
        y1 = floor(y);
        y2 = ceil(y);

        % Compute the pixel values for the four nearest pixels.
        pixel1 = image(y1, x1, :);
        pixel2 = image(y1, x2, :);
        pixel3 = image(y2, x1, :);
        pixel4 = image(y2, x2, :);

        % Compute the fractional distances to each of the four nearest pixels.
        xd = x - x1;
        yd = y - y1;

        % Compute the weighted average of the four nearest pixel values.
        pixelValue = (1 - xd) * (1 - yd) * pixel1 + xd * (1 - yd) * pixel2 + (1 - xd) * yd * pixel3 + xd * yd * pixel4;

        % Assign the computed pixel value to the corresponding pixel in the new image.
        resizedImage(i, j, :) = pixelValue;
    end
end

% Display the original and resized images.
figure;
subplot(1, 2, 1);
imshow(image);
title('Original Image');
subplot(1, 2, 2);
imshow(resizedImage);
title('Bilinear Interpolation Resized Image');

end

% This will gives total 4 images
% HW4(image1, 350, 300)
% HW4(image2, 350, 300)





% Energy Function

function energy = HW4(image)
% Convert the image to grayscale
grayImage = rgb2gray(image);

% Smooth the grayscale image using a Gaussian filter
sigma = 1;
hsize = 2 * ceil(2 * sigma) + 1;
gaussianKernel = fspecial('gaussian', hsize, sigma);
smoothedImage = conv2(double(grayImage), gaussianKernel, 'same');

% Compute the gradients using the Sobel operator
Gx = [-1 0 1; -2 0 2; -1 0 1];
Gy = Gx';
gradX = conv2(smoothedImage, Gx, 'same');
gradY = conv2(smoothedImage, Gy, 'same');

% Compute the energy as the magnitude of the gradients
energy = sqrt(gradX.^2 + gradY.^2);

% Display the original and resized images.
figure;
subplot(1, 2, 1);
imshow(image);
title('Original Image');
subplot(1, 2, 2);
imshow(energy, []);
title('Energy Function');

end

image1 = imread('input_2.png');
HW4(image1)




% Optimal Seam

function [seam, seamEnergy, seamOverlay] = HW4(image)
    % Convert image to grayscale
    grayImage = rgb2gray(image);

    % Compute energy
    energy = computeEnergy(grayImage);

% Compute cumulative energy map
cumulativeEnergyMap = zeros(size(energy));
cumulativeEnergyMap(1,:) = energy(1,:);
for i = 2:size(energy,1)
for j = 1:size(energy,2)
if j == 1
cumulativeEnergyMap(i,j) = energy(i,j) + min(cumulativeEnergyMap(i-1,j), cumulativeEnergyMap(i-1,j+1));
elseif j == size(energy,2)
cumulativeEnergyMap(i,j) = energy(i,j) + min(cumulativeEnergyMap(i-1,j-1), cumulativeEnergyMap(i-1,j));
else
cumulativeEnergyMap(i,j) = energy(i,j) + min([cumulativeEnergyMap(i-1,j-1), cumulativeEnergyMap(i-1,j), cumulativeEnergyMap(i-1,j+1)]);
end
end
end

% Backtrace to find optimal seam
[~, seamIndex] = min(cumulativeEnergyMap(end,:));
seamPath = zeros(size(cumulativeEnergyMap,1), 1);
seamPath(end) = seamIndex;
for i = size(cumulativeEnergyMap,1)-1:-1:1
    if seamIndex == 1
        [~, offset] = min([cumulativeEnergyMap(i,seamIndex), cumulativeEnergyMap(i,seamIndex+1)]);
        seamIndex = seamIndex + offset - 1;
    elseif seamIndex == size(cumulativeEnergyMap,2)
        [~, offset] = min([cumulativeEnergyMap(i,seamIndex-1), cumulativeEnergyMap(i,seamIndex)]);
        seamIndex = seamIndex + offset - 2;
    else
        [~, offset] = min([cumulativeEnergyMap(i,seamIndex-1), cumulativeEnergyMap(i,seamIndex), cumulativeEnergyMap(i,seamIndex+1)]);
        seamIndex = seamIndex + offset - 2;
    end
    seamPath(i) = seamIndex;
end

% Convert image to double array
image = double(image);

% Get seam overlay
seamOverlay = zeros(size(image, 1), size(image, 2), 3);
for i = 1:size(seamPath,1)
    seamOverlay(i,seamPath(i),1) = 255; % set red channel to 255
    seamOverlay(i,seamPath(i),2) = 0; % set green channel to 0
    seamOverlay(i,seamPath(i),3) = 0; % set blue channel to 0
end

seamOverlay(end+1,:,:) = 0; % add a row of zeros to the bottom of the seam overlay

% Convert seamOverlay to integer array
seamOverlay = uint8(seamOverlay);

% Resize the seamOverlay array to match the size of the image array
seamOverlay = imresize(seamOverlay, [size(image,1), size(image,2)]);

% Superimpose the seam overlay on the input image
outputImage = double(image) + double(seamOverlay);

% Convert outputImage to integer array
outputImage = cast(outputImage, 'uint8');

seam = seamPath;
seamEnergy = cumulativeEnergyMap(end, seamPath);

% Display the optimal seam on the color image in red
figure;
imshow(outputImage, 'InitialMagnification', 'fit');
title('Optimal Seam Overlay');

end

function energy = computeEnergy(image)
% Smooth the grayscale image using a Gaussian filter
sigma = 1;
hsize = 2 * ceil(2 * sigma) + 1;
gaussianKernel = fspecial('gaussian', hsize, sigma);
smoothedImage = conv2(double(image), gaussianKernel, 'same');

% Compute the gradients using the Sobel operator
Gx = [-1 0 1; -2 0 2; -1 0 1];
Gy = Gx';
gradX = conv2(smoothedImage, Gx, 'same');
gradY = conv2(smoothedImage, Gy, 'same');

% Compute the energy as the magnitude of the gradients
energy = sqrt(gradX.^2 + gradY.^2);

end

% [seam, seamEnergy, seamOverlay] = HW4(image);





% Seam Carving

% HW4.m file

function [seam, seamEnergy, seamOverlay] = HW4(image)

    % Convert image to grayscale
    grayImage = im2double(rgb2gray(image));

    % Compute the energy of the image
    energy = computeEnergy(grayImage);

    % Compute cumulative energy map
    cumulativeEnergyMap = zeros(size(energy));
    cumulativeEnergyMap(1,:) = energy(1,:);
    for i = 2:size(energy,1)
        for j = 1:size(energy,2)
            if j == 1
                cumulativeEnergyMap(i,j) = energy(i,j) + min(cumulativeEnergyMap(i-1,j), cumulativeEnergyMap(i-1,j+1));
            elseif j == size(energy,2)
                cumulativeEnergyMap(i,j) = energy(i,j) + min(cumulativeEnergyMap(i-1,j-1), cumulativeEnergyMap(i-1,j));
            else
                cumulativeEnergyMap(i,j) = energy(i,j) + min([cumulativeEnergyMap(i-1,j-1), cumulativeEnergyMap(i-1,j), cumulativeEnergyMap(i-1,j+1)]);
            end
        end
    end

    % Backtrace to find optimal seam
    [~, seamIndex] = min(cumulativeEnergyMap(end,:));
    seamPath = zeros(size(cumulativeEnergyMap,1), 1);
    seamPath(end) = seamIndex;
    for i = size(cumulativeEnergyMap,1)-1:-1:1
        if seamIndex == 1
            [~, offset] = min([cumulativeEnergyMap(i,seamIndex), cumulativeEnergyMap(i,seamIndex+1)]);
            seamIndex = seamIndex + offset - 1;
        elseif seamIndex == size(cumulativeEnergyMap,2)
            [~, offset] = min([cumulativeEnergyMap(i,seamIndex-1), cumulativeEnergyMap(i,seamIndex)]);
            seamIndex = seamIndex + offset - 2;
        else
            [~, offset] = min([cumulativeEnergyMap(i,seamIndex-1), cumulativeEnergyMap(i,seamIndex), cumulativeEnergyMap(i,seamIndex+1)]);
            seamIndex = seamIndex + offset - 2;
        end
        seamPath(i) = seamIndex;
    end

    % Get seam overlay
    seamOverlay = zeros(size(image, 1), size(image, 2), 3);
    for i = 1:size(seamPath,1)
        seamOverlay(i,seamPath(i),1) = 255; % set red channel to 255
        seamOverlay(i,seamPath(i),2) = 0; % set green channel to 0
        seamOverlay(i,seamPath(i),3) = 0; % set blue channel to 0
    end

    seamOverlay(end+1,:,:) = 0; % add a row of zeros to the bottom of the seam overlay

    % Convert seamOverlay to integer array
    seamOverlay = uint8(seamOverlay);

    % Resize the seamOverlay array to match the size of the image array
    seamOverlay = imresize(seamOverlay, [size(image,1), size(image,2)]);

    % Superimpose the seam overlay on the input image
    outputImage = double(image) + double(seamOverlay);

    % Convert outputImage to integer array
    outputImage = cast(outputImage, 'uint8');

    seam = seamPath;
    seamEnergy = cumulativeEnergyMap(end, seamPath);

end

function energy = computeEnergy(image)
% Smooth the grayscale image using a Gaussian filter
sigma = 1;
hsize = 2 * ceil(2 * sigma) + 1;
gaussianKernel = fspecial('gaussian', hsize, sigma);
smoothedImage = conv2(double(image), gaussianKernel, 'same');

% Compute the gradients using the Sobel operator
Gx = [-1 0 1; -2 0 2; -1 0 1];
Gy = Gx';
gradX = conv2(smoothedImage, Gx, 'same');
gradY = conv2(smoothedImage, Gy, 'same');

% Compute the energy as the magnitude of the gradients
energy = sqrt(gradX.^2 + gradY.^2);

end


% seamCarvingVideo.m file

% Load image
image = imread('input_2.png');

% Downsize image for faster processing
scaleFactor = 1;
image = imresize(image, scaleFactor);

% Set up VideoWriter object
v = VideoWriter('seam_removal.mp4', 'MPEG-4');
open(v);

% Get the video dimensions
vidWidth = 926;
vidHeight = 617;

% Save original image as first frame of video
frame = double(image);
frame = cast(frame, 'uint8');
frame = imresize(frame, [vidHeight, vidWidth]); % Resize frame
writeVideo(v, frame);

% Initialize variables
nFrames = size(image, 2) - 1; % number of frames needed which is 925
frameIndex = 2; % start with second frame
while size(image, 2) > 1 % stop when image is one pixel wide
    % Compute optimal seam
    [~, seam, seamOverlay] = HW4(image);

    % Remove pixels corresponding to the seam from the image
    for i = 1:size(image, 1)
        image(i, seam(i):floor(end-1), :) = image(i, seam(i)+1:end, :);
    end
    image(:, floor(end), :) = [];

    % Compute energy of modified image
    grayImage = im2double(rgb2gray(image));
    energy = computeEnergy(grayImage);

    % Save current frame as an image
    frame = double(image);
    frame = cast(frame, 'uint8');
    filename = strcat('frame', num2str(frameIndex), '.jpg');

    % Resize frame to match video dimensions
    frame = imresize(frame, [vidHeight, vidWidth]);

    % Resize seamOverlay to have the same number of columns as image
    seamOverlay = imresize(seamOverlay, [size(seamOverlay, 1), size(image, 2)]);

    % Superimpose seam on image and save as next frame of video
    frame = double(image) + double(seamOverlay);
    frame = cast(frame, 'uint8');

    % Resize frame to match video dimensions
    frame = imresize(frame, [vidHeight, vidWidth]);

    % Write resized frame to video
    writeVideo(v, frame);

    % Resize seam overlay to match new image size
    resizedSeamOverlay = imresize(seamOverlay, [size(image,1) size(image,2)]);

    frameIndex = frameIndex + 1;
end

% Close VideoWriter object
close(v);


function energy = computeEnergy(image)
% Smooth the grayscale image using a Gaussian filter
sigma = 1;
hsize = 2 * ceil(2 * sigma) + 1;
gaussianKernel = fspecial('gaussian', hsize, sigma);
smoothedImage = conv2(double(image), gaussianKernel, 'same');

% Compute the gradients using the Sobel operator
Gx = [-1 0 1; -2 0 2; -1 0 1];
Gy = Gx';
gradX = conv2(smoothedImage, Gx, 'same');
gradY = conv2(smoothedImage, Gy, 'same');

% Compute the energy as the magnitude of the gradients
energy = sqrt(gradX.^2 + gradY.^2);

end
