% Gaussian Smoothing

%original grayscale image

% Load the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
gray = im2double(img);
gray = rgb2gray(gray);
else
gray = img;
end

% Display the image
imshow(gray);
title('Original Image');

% Save the image
imwrite(gray, 'original_grayscale_image.jpg');

% Image smoothed with 9 × 9 gaussian kernel with σ = 1

% Read in the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
gray = im2double(img);
gray = rgb2gray(gray);
else
gray = img;
end

% Set the filter size and standard deviation
K = 9;
sigma = 1;

% Create the Gaussian filter
kernel = zeros(K,K);
for i = 1:K
    for j = 1:K
        kernel(i, j) = exp(-((i - (K+1) / 2)^2 + (j - (K+1) / 2)^2) / (2 * sigma^2));
    end
end

% Normalize the filter
kernel = kernel / sum(kernel(:));

% Apply the filter to the image
blurred_img = zeros(size(gray));
for i = floor(K / 2) + 1:size(gray, 1) - floor(K / 2)
    for j = floor(K / 2) + 1:size(gray, 2) - floor(K / 2)
        blurred_img(i, j) = sum(sum(kernel .* double(gray(i - floor(K / 2):i + floor(K / 2), j - floor(K / 2):j + floor(K / 2)))));
    end
end

% Convert the smoothed image back to uint8
blurred_img = uint8(blurred_img);

% Display the blurred images
imshow(blurred_img);

% Set the filter size and standard deviation for 3 more images
K = 3;
sigma = 2;

K = 5;
sigma = 3;

K = 7;
sigma = 4;





% Gradients

%three images using the original image

% Load the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
    gray = rgb2gray(img);
else
    gray = img;
end

% Convert the image to double
gray = im2double(gray);

% Compute the gradient in the x direction
dx = imfilter(gray, [-1 0 1]);

% Compute the gradient in the y direction
dy = imfilter(gray, [-1 0 1]');

% Compute the magnitude of the combined gradients
mag = sqrt(dx.^2 + dy.^2);

% Display the x-gradient image
subplot(2,3,1), imshow(dx); title('x-Gradient Image')

% Display the y-gradient image
subplot(2,3,2), imshow(dy); title('y-Gradient Image')

% Display the magnitude image
subplot(2,3,3), imshow(mag, []); title('Magnitude Image')

%to apply a smoothing filter to the original image and compute the gradients

% Load the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
    gray = rgb2gray(img);
else
    gray = img;
end

% Convert the image to double
gray = im2double(gray);

% Define the filter size and variance
K = 3;
sigma = 1;

% Create the Gaussian kernel
kernel = zeros(K, K);

% Calculate the Gaussian weights
for i = 1:K
    for j = 1:K
        kernel(i, j) = exp(-((i - (K+1) / 2)^2 + (j - (K+1) / 2)^2) / (2 * sigma^2));
    end
end

% Apply the smoothing filter to the image
smoothed_img = imfilter(gray, kernel);

% Compute the gradient in the x direction
dx = imfilter(smoothed_img, [-1 0 1]);

% Compute the gradient in the y direction
dy = imfilter(smoothed_img, [-1 0 1]');

% Compute the magnitude of the combined gradients
mag = sqrt(dx.^2 + dy.^2);

% Display the x-gradient image
subplot(2,3,1), imshow(dx); title('x-Gradient Image')

% Display the y-gradient image
subplot(2,3,2), imshow(dy); title('y-Gradient Image')

% Display the magnitude image
subplot(2,3,3), imshow(mag, []); title('Magnitude Image')





% Non-maximum suppression

% Load the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
    gray = rgb2gray(img);
else
    gray = img;
end

% Convert the image to double
gray = im2double(gray);

% Define the filter size and variance
K = 3;
sigma = 1;

% Create the Gaussian kernel
kernel = zeros(K, K);

% Calculate the Gaussian weights
for i = 1:K
    for j = 1:K
        kernel(i, j) = exp(-((i - (K+1) / 2)^2 + (j - (K+1) / 2)^2) / (2 * sigma^2));
    end
end

% Apply the smoothing filter to the image
smoothed_img = imfilter(gray, kernel);

% Compute the gradient in the x direction
dx = imfilter(smoothed_img, [-1 0 1]);

% Compute the gradient in the y direction
dy = imfilter(smoothed_img, [-1 0 1]');

% Compute the magnitude of the combined gradients
mag = sqrt(dx.^2 + dy.^2);

% Compute the angle of the gradient
theta = atan2(dy, dx);

% Apply non-maximum suppression to the gradient information
nms_mag = zeros(size(mag));
for i = 2:size(mag, 1)-1
    for j = 2:size(mag, 2)-1
        theta = atan2(dy(i,j), dx(i,j));
        if ((theta < -7*pi/8) || (theta >= 7*pi/8) || (-pi/8 <= theta && theta < pi/8))
            if (mag(i,j) > mag(i,j-1) && mag(i,j) > mag(i,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-5*pi/8 <= theta && theta < -3*pi/8) || (3*pi/8 <= theta && theta < 5*pi/8))
            if (mag(i,j) > mag(i-1,j) && mag(i,j) > mag(i+1,j))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-3*pi/8 <= theta && theta < -pi/8) || (5*pi/8 <= theta && theta < 7*pi/8))
            if (mag(i,j) > mag(i-1,j+1) && mag(i,j) > mag(i+1,j-1))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-7*pi/8 <= theta && theta < -5*pi/8) || (1*pi/8 <= theta && theta < 3*pi/8))
            if (mag(i,j) > mag(i-1,j-1) && mag(i,j) > mag(i+1,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        else
            if (mag(i,j) > mag(i-1,j-1) && mag(i,j) > mag(i+1,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        end
    end
end

% Display the non-max-suppressed image
imshow(nms_mag); title('Non-Maximum Suppressed Image')





% Hysterisis

% Load the image
img = imread('circles.gif');

% Convert the image to grayscale if necessary
if size(img, 3) == 3
    gray = rgb2gray(img);
else
    gray = img;
end

% Convert the image to double
gray = im2double(gray);

% Define the filter size and variance
K = 3;
sigma = 1;

% Create the Gaussian kernel
kernel = zeros(K, K);

% Calculate the Gaussian weights
for i = 1:K
    for j = 1:K
        kernel(i, j) = exp(-((i - (K+1) / 2)^2 + (j - (K+1) / 2)^2) / (2 * sigma^2));
    end
end

% Apply the smoothing filter to the image
smoothed_img = imfilter(gray, kernel);

% Compute the gradient in the x direction
dx = imfilter(smoothed_img, [-1 0 1]);

% Compute the gradient in the y direction
dy = imfilter(smoothed_img, [-1 0 1]');

% Compute the magnitude of the combined gradients
mag = sqrt(dx.^2 + dy.^2);

% Compute the angle of the gradient
theta = atan2(dy, dx);

% Apply non-maximum suppression to the gradient information
nms_mag = zeros(size(mag));
for i = 2:size(mag, 1)-1
    for j = 2:size(mag, 2)-1
        theta = atan2(dy(i,j), dx(i,j));
        if ((theta < -7*pi/8) || (theta >= 7*pi/8) || (-pi/8 <= theta && theta < pi/8))
            if (mag(i,j) > mag(i,j-1) && mag(i,j) > mag(i,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-5*pi/8 <= theta && theta < -3*pi/8) || (3*pi/8 <= theta && theta < 5*pi/8))
            if (mag(i,j) > mag(i-1,j) && mag(i,j) > mag(i+1,j))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-3*pi/8 <= theta && theta < -pi/8) || (5*pi/8 <= theta && theta < 7*pi/8))
            if (mag(i,j) > mag(i-1,j+1) && mag(i,j) > mag(i+1,j-1))
                nms_mag(i,j) = mag(i,j);
            end
        elseif ((-7*pi/8 <= theta && theta < -5*pi/8) || (1*pi/8 <= theta && theta < 3*pi/8))
            if (mag(i,j) > mag(i-1,j-1) && mag(i,j) > mag(i+1,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        else
            if (mag(i,j) > mag(i-1,j-1) && mag(i,j) > mag(i+1,j+1))
                nms_mag(i,j) = mag(i,j);
            end
        end
    end
end

% Set the low and high thresholds
low_threshold = 1.7;
high_threshold = 2.3;

% Apply hysteresis to the non-max-suppressed image
binary_image = zeros(size(nms_mag));
strong_edges = (nms_mag > high_threshold);
thresholded_edges = ((nms_mag <= high_threshold) & (nms_mag >= low_threshold));

% Initialize the stack
stack = [];

% Mark all the strong edges
for i = 1:size(nms_mag,1)
    for j = 1:size(nms_mag,2)
        if (strong_edges(i,j))
            binary_image(i,j) = 1;
            stack = [stack [i;j]];
        end
    end
end

% Trace edges using a depth-first search
while ~isempty(stack)
    % Pop the top element from the stack
    current_point = stack(:,end);
    stack = stack(:,1:end-1);

    % Define the neighborhood of the current point
    x = current_point(1);
    y = current_point(2);
    neighborhood = binary_image(x-1:x+1,y-1:y+1);

    % Mark all the connected thresholded edges in the neighborhood
    for i = x-1:x+1
        for j = y-1:y+1
            if (i >= 1 && i <= size(nms_mag,1) && j >= 1 && j <= size(nms_mag,2) && thresholded_edges(i,j) && ~binary_image(i,j))
                binary_image(i,j) = 1;
                stack = [stack [i;j]];
            end
        end
    end
end

% Display the binary image
imshow(binary_image); title('Binary Image');
