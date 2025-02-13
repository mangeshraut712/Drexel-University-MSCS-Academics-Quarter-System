import sys
import time

from bayes_template import Bayes_Classifier
from best_classifier import Best_Classifier

bayes = Bayes_Classifier()
best = Best_Classifier()
if sys.argv[1] == 'bayes':
    start = time.time()
    if len(sys.argv) < 3:
        print("Success rate and Accuracy for the First data set : ",
              bayes.test("validate"))
        print("Success rate and Accuracy for the Second data set : ", bayes.test("test"))
        print("Success rate and Accuracy for the movies_reviews data set : ",
              bayes.test("movies_reviews"))
        print("Script execution time: ", time.time() - start)
    else:
        value = sys.argv[2]
        print(bayes.classify(value))
        print("Script execution time: ", time.time() - start)

else:
    start = time.time()
    if len(sys.argv) < 3:
        print("Accuracy rate and Accuracy for the first data set : ",
              best.test("validate"))
        print("Success rate and Accuracy for the Second data set : ", best.test("test"))
        print("Success rate and Accuracy for the movies_reviews data set : ",
              best.test("movies_reviews"))
        print("Script execution time: ", time.time() - start)
    else:
        value = sys.argv[2]
        print(best.classify(value))
        print("Script execution time: ", time.time() - start)
