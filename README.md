# Multi Module Template

This project is a setup for multimodule repositories, specifically for at least a SpringBoot Backend and an Angular Frontend. 

# How to build
By running maven build from the builder module, it builds the frontend and backend and then adds the frontend files to the backend where it serves them on /frontend.
It also has a builtin retry for index.html, so a custom nginx config to retry index.html if it does not find the html file is not needed.
