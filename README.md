﻿# Tesseract-Calculator

This little program calculates how each tesseract in a penteract is connected.

Multi-dimensional cubes follow some simple rules.  
The number of vertices is twice the number of the previous hypercube or 2<sup>n</sup> (with 0-cube being a single point).  
Each n-cube is made out of all lower dimensional hypercubes. So a 4-cube (n=4) is made from 8 cubes (3-cubes), 24 squares (2-cubes), 32 lines (1-cubes) and 16 vertices (0-cubes).

A penteract (5-cube) is made from 10 tesseracts (4-cubes). At some point I wanted to construct a penteract. I wanted to understand how these multidimensional cubes worked and I had some practical ideas for it (for a dungeon in a game e.g.).  
The problem I ran into was how the tesseracts were connected together to make the penteract. You can connect each cube (3-cube) in a tesseract with a doorway and thus create a self contained universe for 3 dimensional beings like us (think if The Cube movies).  
There each cube is connected with a 2 dimensional doorway (same goes for a 2 dimensional map, where doorways are 1 dimensional) so each cube in the penteract is connected to another cube in a different tesseract with a 3 dimensional doorway.

I needed to calculate for each cube in each tesseract to which cube they are connected. So many years ago I wrote this java program to calculate that for me.

The output shows how each cube is connected.
