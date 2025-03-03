## Changelog

Comments made on gradescope: 

Your blocky does not "play correctly". after the first few blocks, the next blocks seem to stop at increasingly higher levels on the board until the game simply crashes.

Additionally, your shuffling algorithm is working incorrectly - one block-kind should not be generated again until all the other block kinds have been generated once.

CHANGES MADE: 
- Changed shuffle so that it does not have replacement, meaning all of the blocks will be chosen before the same one is chosen again.
   I did this by making the for loop go backwards so that it could not go over any variables it already had, and made my temp variable work correctly as it was not before. 
- changed trySpawBlock so that it checks if its gone through all of the blocks before making a new shuffled array, so this should shuffle correclty now. 
- got the blocks to stack on top of eachother as opposed to just stopping at like two places above of the previous block 
    to do this I first tried to change a bunch of things in the board that didn't end up working, eventually I realized that it may just be a graphics problem, so I was able to go through the logic of BlockyPanel and do some trial and error to find out that's where the issues were. I changed some of the math/numbers and that let the pieces go on top of eachother, but they were then going too low and so I had to make some changes to the starting height in board, spesifically some >= or <= that should have just been > or <. 