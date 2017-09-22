# stats

[![CircleCI](https://circleci.com/gh/tiagoantao/stats.svg?style=svg)](https://circleci.com/gh/tiagoantao/stats)

A statistical library implemented in ClojureScript

# Functionality

For now we are trying to build a statistics library that can work on the browser.
Also a 100% web-based teaching tool.

For future plans - including server-side support via node.js - see [Vision](#vision) below.

# Installation

Currently under heavy development. Still no installation instructions. Stay tuned.

# Building

## Web content

You need `node.js`. Go into the web directory and (after `npm install`) do `node build.js`.
You need to do this everytime you change the HTML content.

## Code

We use a fairly standard lein configuration with figwheel. 
You need to generate the web content before using lein.

# Vision
[](#vision)

## Short-term

The short term objective is to build a statistical library with ClojureScript that can
be used in the browser. It should allow all the basic statistics of an introductory
undergraduate course in the field.

It is not expected to work with big data. Not only because it will be browser based,
with its inherent limitations, but also because there is no effort to produce very
performant code.

Considerable effort will be put on creating a web interface to teach statistics.
This should be production quality.

## Long-term

There are 3 main long-term objectives for the second version:

1. Learn from version 1 in terms of user interface and improve on that

2. Create a node.js backend

3. Invest strongly in performance. For the browser based backend that means
re-implementing some code in JavaScript. *Most importantly* for the node.js
version part of the code will be implemented in a lower level language and
include both multi-core and GPU processing. There is no reason why the node
version cannot be the fastest statistical library available.
