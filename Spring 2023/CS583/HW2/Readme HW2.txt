ReadMe HW2

Circles.gif is the main image that is used for the pipeline.

Gaussian Smoothing is good to start for the assignment while calculating the kernel using the formula :
kernel(i, j) = exp(-((i - (K+1) / 2)^2 + (j - (K+1) / 2)^2) / (2 * sigma^2));

Gradients without smoothed and with smoothed see some changes.

Non-Maximum suppression on the gradient magnitude image confuses me while doing it but try my best.

Hysteresis is good to play with the images and match the low and high threshold values found something like low-value increases and high value decreases to see the inner and outer circle changes.

input_1_example is my image for using this pipeline first I tried with the color image it doesn't work so I use grayscaled image and it gives me perfect results.