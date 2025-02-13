%Generate Fake Data

%Create a 400x400 binary image
img = false(400,400);

% Generate line
m = 1;
b = -100;
x = 1:400;
y = m*x + b;
y = round(y);
for i = 1:length(x)
    if y(i) > 0 && y(i) <= 400
        img(y(i), x(i)) = true;
    end
end

% Generate circle
x0 = 100;
y0 = 200;
r = 50;
theta = linspace(0,2*pi);
x = round(x0 + r*cos(theta));
y = round(y0 + r*sin(theta));
for i = 1:length(x)
    if x(i) > 0 && x(i) <= 400 && y(i) > 0 && y(i) <= 400
        img(y(i), x(i)) = true;
    end
end

% Display image
imshow(img);





% Apply Hough Transform for a line

% Create a 400x400 binary image
img = false(400, 400);

% Generate line
m = 1;
b = -100;
x = 1:400;
y = m * x + b;
y = round(y);
for i = 1:length(x)
    if y(i) > 0 && y(i) <= 400
        img(y(i), x(i)) = true;
    end
end

% Generate circle
x0 = 100;
y0 = 200;
r = 50;
theta = linspace(0, 2 * pi);
x = round(x0 + r * cos(theta));
y = round(y0 + r * sin(theta));
for i = 1:length(x)
    if x(i) > 0 && x(i) <= 400 && y(i) > 0 && y(i) <= 400
        img(y(i), x(i)) = true;
    end
end

% Define Hough Transform parameters
numTheta = 180;
thetaRange = -90:1:89;
maxRho = round(sqrt(size(img, 1)^2 + size(img, 2)^2));
rhoRange = -maxRho:maxRho;

% Create accumulator matrix
accumulator = zeros(length(rhoRange), length(thetaRange));

% Perform Hough Transform
for x = 1:size(img, 2)
    for y = 1:size(img, 1)
        if img(y, x) == true
            for thetaIndex = 1:length(thetaRange)
                theta = thetaRange(thetaIndex);
                rho = x * cosd(theta) + y * sind(theta);
                rhoIndex = round(rho) + maxRho + 1;
                accumulator(rhoIndex, thetaIndex) = accumulator(rhoIndex, thetaIndex) + 1;
            end
        end
    end
end

% Find maximum value in accumulator
maxValue = max(accumulator(:));
[maxRhoIndex, maxThetaIndex] = find(accumulator == maxValue);
rhoMax = rhoRange(maxRhoIndex) - maxRho - 1;
thetaMax = thetaRange(maxThetaIndex);

% Convert (theta, rho) to (m, b)
m = -cosd(thetaMax) / sind(thetaMax);
b = rhoMax / sind(thetaMax);

% Plot Hough Transform
imshow(accumulator,[],'XData',thetaRange,'YData',rhoRange,'InitialMagnification','fit');






% Hough Transform for Circle

% Create a 400x400 binary image
img = false(400,400);

% Generate line
m = 1;
b = -100;
x = 1:400;
y = m*x + b;
y = round(y);
img(sub2ind(size(img), y(y > 0 & y <= 400), x(y > 0 & y <= 400))) = true;

% Generate circle
x0 = 100;
y0 = 200;
r = 50;
theta = linspace(0,2*pi,1000); % increase number of points for smoother circle
x = round(x0 + r*cos(theta));
y = round(y0 + r*sin(theta));
img(sub2ind(size(img), y(y > 0 & y <= 400), x(x > 0 & x <= 400))) = true;

% Define range of r values
rMin = 1;
rMax = ceil(sqrt(sum(size(img).^2)));
rRange = rMin:rMax;

maxRadius = ceil(sqrt(size(img,1)^2 + size(img,2)^2)); % use size(img,1) instead of size(x,1)
H = zeros(size(img,1),size(img,2),maxRadius);

for i=1:size(x,2)
    for r=1:maxRadius

        a = round(x(i) - r * cos(theta));
        b = round(y(i) - r * sin(theta));

        % Check if indices are within bounds
        if (all(a >= 1) && all(a <= size(img,2)) && all(b >= 1) && all(b <= size(img,1)))
            H(b,a,r) = H(b,a,r) + 1;
        end
    end
end

% Find maximum bin in Hough space
[maxValue,linearIndex] = max(H(:));
[y0Index,x0Index,rIndex] = ind2sub(size(H),linearIndex);
x0 = x0Index;
y0 = y0Index;
r = rRange(rIndex);
fprintf('(x0, y0, r) = (%d, %d, %d)\n', x0, y0, r);

% Plot Hough space slice where r = rmax
rmaxIndex = find(rRange == r);
slice = squeeze(H(:,:,rmaxIndex));
figure;
imshow(slice, []);
title(sprintf('Hough Transform slice (r = %d)', r));

% Plot detected circle on original image
figure;
imshow(img);
hold on;
viscircles([x0,y0],r,'EdgeColor','r');
title(sprintf('Detected circle (x0 = %d, y0 = %d, r = %d)', x0, y0, r));





%Apply to a Real Image

% Load image
I = imread('circles.gif');

% Convert image to grayscale
if size(I,3) == 3
    I = rgb2gray(I);
end

% Detect edges using Sobel operator
hx = [-1 0 1; -2 0 2; -1 0 1];
hy = hx';
Gx = conv2(double(I), hx, 'same');
Gy = conv2(double(I), hy, 'same');
G = sqrt(Gx.^2 + Gy.^2);

% Threshold edges
T = max(G(:)) * 0.5;
BW = G > T;

% Detect circles using Hough Circle Transform
minR = 20;
maxR = 100;
radii = minR:maxR;
H = zeros(size(BW,1), size(BW,2), length(radii));
for r = radii
    for x = 1:size(BW,1)
        for y = 1:size(BW,2)
            if BW(x,y) == 1
                for theta = 0:360
                    a = round(x - r * cosd(theta));
                    b = round(y - r * sind(theta));
                    if a > 0 && a <= size(BW,1) && b > 0 && b <= size(BW,2)
                        H(a,b,r-minR+1) = H(a,b,r-minR+1) + 1;
                    end
                end
            end
        end
    end
end

% Find maximum value in Hough space
[maxH, maxIndex] = max(H(:));
[x0, y0, rIndex] = ind2sub(size(H), maxIndex);
r0 = radii(rIndex);

% Display subplots of original image, binary image, and superimposed image
figure
subplot(1,3,1)
imshow(I)
title('Original Image')
subplot(1,3,2)
imshow(BW)
title('Binary Image')
subplot(1,3,3)
imshow(I)
hold on
theta = linspace(0,2*pi);
x = x0 + r0*cos(theta);
y = y0 + r0*sin(theta);
plot(y,x,'r','LineWidth',2);
title('Circles')
hold off

% Display (x0,y0,r) values for the most dominant circle
fprintf('(x0,y0,r) = (%f,%f,%f)\n', x0, y0, r0);





% Additional Circles

% Load image
I = imread('circles.gif');

% Convert image to grayscale
if size(I,3) == 3
    I = rgb2gray(I);
end

% Detect edges using Sobel operator
hx = [-1 0 1; -2 0 2; -1 0 1];
hy = hx';
Gx = conv2(double(I), hx, 'same');
Gy = conv2(double(I), hy, 'same');
G = sqrt(Gx.^2 + Gy.^2);

% Threshold edges
T = max(G(:)) * 0.3;
BW = G > T;

% Detect circles using Hough Circle Transform
minR = 20;
maxR = 100;
radii = minR:maxR;
H = zeros(size(BW,1), size(BW,2), length(radii));
for r = radii
    for x = 1:size(BW,1)
        for y = 1:size(BW,2)
            if BW(x,y) == 1
                for theta = 0:360
                    a = round(x - r * cosd(theta));
                    b = round(y - r * sind(theta));
                    if a > 0 && a <= size(BW,1) && b > 0 && b <= size(BW,2)
                        H(a,b,r-minR+1) = H(a,b,r-minR+1) + 1;
                    end
                end
            end
        end
    end
end

% Find local maxima in Hough space manually
maxH = zeros(size(H));
for x = 2:size(H,1)-1
    for y = 2:size(H,2)-1
        for r = 2:size(H,3)-1
            if H(x,y,r) > H(x-1,y,r) && H(x,y,r) > H(x+1,y,r) && ...
               H(x,y,r) > H(x,y-1,r) && H(x,y,r) > H(x,y+1,r) && ...
               H(x,y,r) > H(x,y,r-1) && H(x,y,r) > H(x,y,r+1)
                maxH(x,y,r) = H(x,y,r);
            end
        end
    end
end

% Find all circles in the image
threshold = max(maxH(:)) * 0.6;
maxIndex = find(maxH >= threshold);
[x0, y0, rIndex] = ind2sub(size(H), maxIndex);
r0 = radii(rIndex);

% Display subplots of original image, binary image, and superimposed image
figure
subplot(1,3,1)
imshow(I)
title('Original Image')
subplot(1,3,2)
imshow(BW)
title('Binary Image')
subplot(1,3,3)
imshow(I)
hold on
theta = linspace(0,2*pi);
for i = 1:length(r0)
    x = x0(i) + r0(i)*cos(theta);
    y = y0(i) + r0(i)*sin(theta);
    plot(y,x,'r','LineWidth',1);
end
title('Circles')
hold off

% Display (x0,y0,r) values for all detected circles
for i = 1:length(r0)
    fprintf('(x0,y0,r) #%d: (%f,%f,%f)\n', i, x0(i), y0(i), r0(i));
end
