ReadMe HW4Theory questions 
1. Using imresize(I,[6,5],'nearest'); will give 5.
   Using formula v = (1-dx)*(1-dy)*q11 + dx*(1-dy)*q21 + (1-dx)*dy*q12 + dx*dy*q22 we will get the v.
   
Image Resizing
input_1.png and input_2.png are the two images where I perform the resizing.

Energy Function
Convert the image to grayscale apply filter using conv2.

Optimal Seam
Set the R=255 and G and B to 0.

Seam Carving
Set the width and height to video frame and then number of frames will be width - 1.