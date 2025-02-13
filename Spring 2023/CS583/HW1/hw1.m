% 1 RGB → Grayscale

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to grayscale
gray = 0.2989*im(:,:,1) + 0.5870*im(:,:,2) + 0.1140*im(:,:,3);

% Display the grayscale image
imshow(gray);

% Save the grayscale image
imwrite(gray, 'grayscale.jpg');





% 2 RGB → Binary

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to grayscale
gray = 0.2989*im(:,:,1) + 0.5870*im(:,:,2) + 0.1140*im(:,:,3);

% Threshold the grayscale image at 75%
thresh = 0.25*max(gray);
binary = gray > thresh;

% Save the grayscale image
imwrite(gray, 'grayscale.jpg');

% Save the binary image
imwrite(binary, 'binary_25_t.jpg');

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to grayscale
gray = 0.2989*im(:,:,1) + 0.5870*im(:,:,2) + 0.1140*im(:,:,3);

% Threshold the grayscale image at 75%
thresh = 0.5*max(gray);
binary = gray > thresh;

% Save the grayscale image
imwrite(gray, 'grayscale.jpg');

% Save the binary image
imwrite(binary, 'binary_50_t.jpg');

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to grayscale
gray = 0.2989*im(:,:,1) + 0.5870*im(:,:,2) + 0.1140*im(:,:,3);

% Threshold the grayscale image at 75%
thresh = 0.75*max(gray);
binary = gray > thresh;

% Save the grayscale image
imwrite(gray, 'grayscale.jpg');

% Save the binary image
imwrite(binary, 'binary_75_t.jpg');





% 3 Gamma Correction

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to double data type
im = im2double(im);

% Apply gamma correction
gamma = [0.2];
for i = 1:3
  im(:,:,i) = im(:,:,i).^0.2;
end

% Save the gamma-corrected images
imwrite(im, 'gamma_0.2.jpg');

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to double data type
im = im2double(im);

% Apply gamma correction
gamma = [1];
for i = 1:3
  im(:,:,i) = im(:,:,i).^1;
end

% Save the gamma-corrected images
imwrite(im, 'gamma_1.jpg');

% Read in the color image
im = imread('input_1.jpg');

% Convert the image to double data type
im = im2double(im);

% Apply gamma correction
gamma = [50];
for i = 1:3
  im(:,:,i) = im(:,:,i).^50;
end

% Save the gamma-corrected images
imwrite(im, 'gamma_50.jpg');





% 4 Changing Hue

% Load the image
img = imread('input_1.jpg');

% Convert the image from uint8 to double
img = im2double(img);

% Get the size of the image
[m,n,p] = size(img); 

% Initialize the HSV image
hsv = zeros(m,n,p);

% Convert RGB to HSV
for i = 1:m
    for j = 1:n
        % Get the RGB values for the current pixel
        R = img(i,j,1);
        G = img(i,j,2);
        B = img(i,j,3);
        
        % Calculate the value (V) of the pixel in HSV color space
        V = max([R,G,B]);
        
        % Calculate the saturation (S) of the pixel in HSV color space
        delta = max([R,G,B]) - min([R,G,B]);
        if V == 0
            S = 0;
        else
            S = delta / V;
        end
        
        % Calculate the hue (H) of the pixel in HSV color space
        if delta == 0
            H = 0;
        elseif V == R
            H = 60 * mod((G - B) / delta, 6);
        elseif V == G
            H = 60 * ((B - R) / delta + 2);
        elseif V == B
            H = 60 * ((R - G) / delta + 4);
        end
        
        % Set the HSV values for the current pixel
        hsv(i,j,:) = [H,S,V];
    end
end

% Increase the hue by 50 degrees
hsv(:,:,1) = hsv(:,:,1) + 50;

% Make sure hue values are within [0,360]
hsv(:,:,1) = mod(hsv(:,:,1),360);

% Initialize the RGB image
rgb = zeros(m,n,p);

% Convert HSV to RGB
for i = 1:m
    for j = 1:n
        % Get the HSV values for the current pixel
        H = hsv(i,j,1);
        S = hsv(i,j,2);
        V = hsv(i,j,3);
        
        % Calculate the RGB values for the current pixel
        C = V * S;
        X = C * (1 - abs(mod(H/60,2) - 1));
        m = V - C;
        
        if H >= 0 && H < 60
            R_prime = C;
            G_prime = X;
            B_prime = 0;
        elseif H >= 60 && H < 120
            R_prime = X;
            G_prime = C;
            B_prime = 0;
        elseif H >= 120 && H < 180
            R_prime = 0;
            G_prime = C;
            B_prime = X;
        elseif H >= 180 && H < 240
            R_prime = 0;
            G_prime = X;
            B_prime = C;
        elseif H >= 240 && H < 300
            R_prime = X;
            G_prime = 0;
            B_prime = C;
        elseif H >= 300 && H < 360
            R_prime = C;
            G_prime = 0;
            B_prime = X;
        end
        
        % Set the RGB values for the current pixel
        rgb(i,j,:) = [R_prime+m,G_prime+m,B_prime+m];
    end
end

% Convert the image from double to uint8 and save it to disk.
imwrite(im2uint8(rgb), 'hue_image.jpg'); 





% 5 Histograms

% Grayscale histogram

img = imread('input_1.jpg');
img = double(img);

grayImg = 0.2989 * img(:,:,1) + 0.5870 * img(:,:,2) + 0.1140 * img(:,:,3);
grayHist = zeros(1, 256);
for i = 1:numel(grayImg)
    grayHist(floor(grayImg(i))+1) = grayHist(floor(grayImg(i))+1) + 1;
end
grayHist = grayHist / sum(grayHist);

figure('Name', 'Grayscale Histogram');
bar(linspace(0, 255, 256), grayHist, 'hist');
xlabel('Intensity');
ylabel('Frequency');
title('Grayscale Histogram');

% Red channel histogram

img = imread('input_1.jpg');
img = double(img);

redHist = zeros(1, 256);
for i = 1:numel(img(:,:,1))
    redHist(floor(img(i))+1) = redHist(floor(img(i))+1) + 1;
end
redHist = redHist / sum(redHist);

figure('Name', 'Redscale Histogram');
bar(linspace(0, 255, 256), redHist, 'hist');
xlabel('Intensity');
ylabel('Frequency');
title('Redscale Histogram');

% Green channel histogram

img = imread('input_1.jpg');
img = double(img);

greenHist = zeros(1, 256);
for i = 1:numel(img(:,:,2))
    greenHist(floor(img(i))+1) = greenHist(floor(img(i))+1) + 1;
end
greenHist = greenHist / sum(greenHist);

figure('Name', 'Greenscale Histogram');
bar(linspace(0, 255, 256), greenHist, 'hist');
xlabel('Intensity');
ylabel('Frequency');
title('Greenscale Histogram');

% Blue channel histogram

img = imread('input_1.jpg');
img = double(img);

blueHist = zeros(1, 256);
for i = 1:numel(img(:,:,3))
    blueHist(floor(img(i))+1) = blueHist(floor(img(i))+1) + 1;
end
blueHist = blueHist / sum(blueHist);

figure('Name', 'Bluescale Histogram');
bar(linspace(0, 255, 256), blueHist, 'hist');
xlabel('Intensity');
ylabel('Frequency');
title('Bluescale Histogram');