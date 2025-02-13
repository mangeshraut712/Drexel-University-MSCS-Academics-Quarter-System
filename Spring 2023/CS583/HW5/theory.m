clc;
clc;
clear;

% Define image pixel intensity values and initial reference vectors
I = [1 2 0; 0 1 3];
a1 = [1];
a2 = [2];

% Initialize cluster assignments
cluster_assignments = zeros(size(I));

% Initialize flag for checking if cluster assignments have changed
changed = true;

% Initialize iteration counter
iter = 0;

while changed
    % Reset flag
    changed = false;
    
    % Display current iteration, reference vectors, and cluster assignments
    disp(['Iteration ' num2str(iter)])
    disp('Reference vector a1:')
    disp(a1)
    disp('Reference vector a2:')
    disp(a2)
    disp('Cluster assignments:')
    disp(cluster_assignments)
    
    % Assign each observation (pixel) to the reference vector it is closest to
    for i = 1:numel(I)
        d1 = norm(I(i) - a1);
        d2 = norm(I(i) - a2);
        if d1 < d2
            new_assignment = 1;
        else
            new_assignment = 2;
        end
        if new_assignment ~= cluster_assignments(i)
            cluster_assignments(i) = new_assignment;
            changed = true;
        end
    end
    
    % Update reference vectors to be the means of their members
    a1 = mean(I(cluster_assignments == 1));
    a2 = mean(I(cluster_assignments == 2));
    
    % Increment iteration counter
    iter = iter + 1;
end

% Display final reference vectors and cluster assignments
disp(['Iteration ' num2str(iter)])
disp('Reference vector a1:')
disp(a1)
disp('Reference vector a2:')
disp(a2)
disp('Cluster assignments:')
disp(cluster_assignments)

% Get the size of the image
[m, n] = size(I);

% Initialize the weight matrix
W = zeros(m * n);

% Compute the weights between pixels
for i = 1:m
    for j = 1:n
        for k = 1:m
            for l = 1:n
                if i ~= k || j ~= l
                    W((i - 1) * n + j, (k - 1) * n + l) = exp(-((I(i,j) - I(k,l))^2 + (i - k)^2 + (j - l)^2));
                end
            end
        end
    end
end

% Set diagonal elements to 0 (pixel not connected to itself)
W(1:m*n+1:end) = 0;

% Construct the degree matrix D and the weight matrix W for the graph
D = diag(sum(W, 2));
L = D - W;

% Perform eigen-decomposition of the Laplacian matrix L
[V, D] = eig(L);

% Find the Fiedler value and Fiedler vector
fiedlerValue = D(2,2);
fiedlerVector = V(:,2);

% Partition the graph into two groups using the Fiedler vector
group1 = find(fiedlerVector >= 0);
group2 = find(fiedlerVector < 0);

% Reshape the image into a column vector
I = I(:);

% Get the pixel values that belong to each group
group1Pixels = I(group1);
group2Pixels = I(group2);

% Display weight matrix
disp("Weight matrix:")
disp(W)

% Display the pixels belonging to each group
disp("Group 1 pixels:")
disp(group1Pixels)
disp("Group 2 pixels:")
disp(group2Pixels)

% Create new image with pixels in group 1 set to 1 and pixels in group 2 set to 0
I_cut = zeros(size(I));
I_cut(group1) = 1;

% Display original image and cut image in one figure
figure;
subplot(1, 2, 1);
imagesc(reshape(I, m, n));
title('Original Image');
subplot(1, 2, 2);
imagesc(reshape(I_cut, m, n));
title('Cut Image');
