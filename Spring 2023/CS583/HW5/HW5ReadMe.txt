Features of the Program:

The program classifies images using grayscale histograms and HOG features.
It computes grayscale histograms with 256 bins for each image.
It divides the dataset into training and validation subsets.
It performs k-nearest neighbors classification using histogram intersection as the similarity metric.
It computes the accuracy of the classification.
It displays examples of correctly and incorrectly labeled images.

Entry-Point Script Name: HW5.m

Instructions to Run the Script:

Open MATLAB.
Set the current directory to the location of the classification_script.m file.
Make sure you have the CarData folder containing the dataset in the same directory.
Run the script by typing HW5 in the MATLAB command window and pressing Enter.
The script will process the images, perform classification, compute accuracy, and display example images.

The positive car images are located in the CarData/TrainImages directory with file names starting with pos-.
The negative non-car images are located in the CarData/TrainImages directory with file names starting with neg-.
The script saves the computed feature vectors and labels to feature_vectors.mat and labels.mat files for future use.