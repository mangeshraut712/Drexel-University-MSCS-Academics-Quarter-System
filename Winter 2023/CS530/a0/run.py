from flask import Flask, render_template, send_file
import os


app = Flask(__name__)


@app.route('/')
def home():
    return render_template('home.html', message='Hello World!')


if __name__ == '__main__':
    app.run(host='localhost', port=8080, debug=True)
