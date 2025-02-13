#!/bin/sh
if [ "$#" -eq 2 ]; then
    python3 evaluate.py "$1" "$2"
else
    python3 evaluate.py "$1"
fi
