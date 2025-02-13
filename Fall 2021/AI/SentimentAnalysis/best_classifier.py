import math
import os
import pickle
import re
import shutil
import time
from os.path import exists


class Best_Classifier:
    total_words_positive = 0
    total_words_negative = 0
    total_words = 0
    positive = {}
    negative = {}

    def __init__(self, trainDirectory="movie_reviews/"):
        if exists("positive_best.p") or exists("negative_best.p"):
            self.positive = pickle.load(open("positive_best.p", "rb"))
            self.negative = pickle.load(open("negative_best.p", "rb"))
            self.total_words_positive, self.total_words_negative = self.find_totals()
            self.total_words = self.total_words_positive + self.total_words_negative
        else:
            self.train()
            self.positive = pickle.load(open("positive_best.p", "rb"))
            self.negative = pickle.load(open("negative_best.p", "rb"))
            self.total_words_positive, self.total_words_negative = self.find_totals()
            self.total_words = self.total_words_positive + self.total_words_negative

    def find_totals(self):
        '''find the total number of positive and negative words seperately and then combine them both'''
        positive = 0
        negative = 0
        for key in self.positive:
            positive += self.positive[key]
        for key in self.negative:
            negative += self.negative[key]
        return positive, negative

    def test(self, address):
        '''This the test sample to validate and test the performance of the training'''
        true_positive = 0
        true_negative = 0
        false_positive = 0
        false_negative = 0
        wrong = 0
        file_list = []
        for f_object in os.walk("./" + address):
            file_list = f_object[2]
            break
        for file in file_list:
            filepath = self.loadFile("./" + address + "/" + file)
            file_rating = file.split("-")[1]
            # token = self.intelligent_tokenizer(
            #     filepath)
            result = self.classify(filepath)
            if int(file_rating) > 3 and result == "positive":
                true_positive += 1
            elif int(file_rating) >= 4 and result != 'positive':
                false_negative += 1
                wrong += 1
            elif int(file_rating) <= 2 and result != "negative":
                false_positive += 1
                wrong += 1
            elif int(file_rating) <= 2 and result == "negative":
                true_negative += 1
            else:
                wrong += 1
        accuracy = (true_negative + true_positive) / (
                true_negative + true_positive + false_positive + false_negative)
        precision = true_positive / (true_positive + false_positive)
        recall = true_positive / (true_positive + false_negative)
        f_score = (2 * precision * recall) / (precision + recall)
        return accuracy, f_score

    def intelligent_tokenizer(self, sText):
        tokens = []
        sText = sText.split()
        for word in sText:
            if len(word) >= 3:
                word = word.lower()
                for char in word:
                    if char in " ?.!/;,:":
                        word = word.replace(char, '')
                tokens.append(word)
        return tokens

    def train(self):
        '''Trains the Naive Bayes Sentiment Classifier.'''
        file_list = []
        for f_object in os.walk("./train"):
            file_list = f_object[2]
            break
        for file in file_list:
            filepath = self.loadFile("./train/" + file)
            file_rating = file.split("-")[1]
            token = self.intelligent_tokenizer(
                filepath)
            for word in token:
                if int(file_rating) > 3:
                    if word in self.positive:
                        self.positive[word] = self.positive[word] + 1
                    else:
                        self.positive[word] = 1
                else:
                    if word in self.negative:
                        self.negative[word] = self.negative[word] + 1
                    else:
                        self.negative[word] = 1
        pickle.dump(self.positive, open("positive_best.p", "wb"))
        pickle.dump(self.negative, open("negative_best.p", "wb"))

    def classify(self, sText):
        sum_positive = math.log(self.total_words_positive / self.total_words)
        sum_negative = math.log(self.total_words_negative / self.total_words)
        token = self.intelligent_tokenizer(sText)
        for word in token:
            if word in self.positive:
                # laplace smoothing
                probability = (
                                      self.positive[word] + 1) / (self.total_words_positive + len(self.positive))
                sum_positive += math.log(probability)
            else:
                probability = 1 / \
                              (self.total_words_positive + len(self.positive))
                sum_positive += math.log(probability)
            if word in self.negative:
                probability = (
                                      self.negative[word] + 1) / (self.total_words_negative + len(self.negative))
                sum_negative += math.log(probability)
            else:
                probability = 1 / \
                              (self.total_words_negative + len(self.negative) + 1)
                sum_negative += math.log(probability)
        prob_positive = math.exp(sum_positive)
        prob_negative = math.exp(sum_negative)
        if prob_positive > prob_negative:
            return "positive"
        return "negative"

    def loadFile(self, sFilename):
        '''Given a file name, return the contents of the file as a string.'''

        f = open(sFilename, "r")
        sTxt = f.read()
        f.close()
        return sTxt
