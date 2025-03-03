# Blocky Game

_(Gamewerks corporation internal code—do not share!)_

## Credits

Primary developer: Emily Lackershire

CHANGES MADE IN CHANGELOG.md FILE 

### Resources Used

+ Java 17 and Java 23
+ IDE: Apache Netbeans
+ Github
+ Assignment from Professor Osera of Grinnell college
  https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html
+ This wikipedia article about the fischer-yates shuffle was given in our assignment.
  https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
+ Reaserched how to get a random number in java for the shuffle method.
  https://www.geeksforgeeks.org/java-math-random-method-examples/
+ I was not sure if there was an easy way to make a copy of an array in Java, so I looked that up and there is!
  https://www.geeksforgeeks.org/array-copy-in-java/
+ When doing testing, I remembered there was a way to easily print an array, but I could not remember what it was, so I 
  looked it up.
  https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
+

## Changelog
commit b1ea5733b69d302818bd7f235d113cc55a4c518a (HEAD -> main, origin/main, origin/HEAD)
Author: Emily <lackersh@grinnell.edu>
Date:   Mon Mar 3 12:53:06 2025 -0600

    deleted helper function I no loger needed and updated readme

commit 54d4c12010ad491b5f7647656c5eb5a5823a9c12
Author: Emily <lackersh@grinnell.edu>
Date:   Mon Mar 3 12:49:21 2025 -0600

    finnaly got the pieces to stack on top of eachother!!!

commit 647ca0806e7495d8dc12719ad81a8ff3e2fa5dbd
Author: Emily <lackersh@grinnell.edu>
Date:   Mon Mar 3 10:09:00 2025 -0600

    fixed error I accidentally created where pieces just disapeared :(

commit c842fc86b04bfe159ad76d78563e866f2cc279e1
Author: Emily <lackersh@grinnell.edu>
Date:   Sun Mar 2 14:24:35 2025 -0600

commit d9eb66f8fae76a30fa63e31a07f8afa3ae86be84
Author: Emily <lackersh@grinnell.edu>
Date:   Sun Mar 2 14:22:47 2025 -0600

    updated so shuffle acctually changes the piece each time, not just the array doesn't have replacement. 

commit bcc2af713eaef4c1d3d7eae56ded06cc7a8aff83
Author: Emily <lackersh@grinnell.edu>
Date:   Sun Mar 2 12:33:28 2025 -0600

    fixed errros that kinda just randomly appeared

commit 197ed7d1f223913a668a90c1ad07b8d240cf30c9
Author: Emily <lackersh@grinnell.edu>
Date:   Sat Mar 1 18:56:09 2025 -0600

    made changelog for updates

commit 7eca429143456ca191c075f52f5eb235a9113ac6
Author: Emily <lackersh@grinnell.edu>
Date:   Sat Mar 1 18:50:14 2025 -0600

    updated shuffle so that it does not have replacement, meaning each piece will be chosen before the same one is chosen again

commit d2b938d3ff95a7810f621b6f21de09b6a780cdbe
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Wed Feb 12 23:24:09 2025 -0600

    Update README.md

    added git log

commit 319c120a43284e307fc24a56c05bb5fa6bcf5a21
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Wed Feb 12 23:01:12 2025 -0600

    Update BlockyGame.java

    tried to fix the block boundaries so they actually go on top of each other

commit 1b79e0443104f2b13786ac8d966cc8556847ec3b
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Wed Feb 12 22:59:52 2025 -0600

    Update Board.java

    tried to fix some things with the delete so that the rows would actually delete, it didn't work unfortunately.

commit e94e9b69b32e3c05d870c046749bb0b633dfdf50
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Tue Feb 11 23:08:26 2025 -0600

    Update Board.java

    delete row is no longer causing erro

commit ee80ab0084894e4ccb16d9aa111d515e9dc1b8fe
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Tue Feb 11 22:13:07 2025 -0600

    Update Board.java

    updated add to well

commit 0626c2c2089c8d052a33a0041dc977adbb340f69
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 15:42:47 2025 -0600

    Update README.md

    uploaded resources used

commit 7b6f28d02a68e1f7f0ac3da403db3a3f53deaf3d
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 15:36:55 2025 -0600

    Update BlockyGame.java

    added break in switch statement so that right does not just make the program freeze

commit fd7a0b0cfa3160cfedd7441208936599809fd42e
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 13:30:31 2025 -0600

    Update Board.java

    No more out of bounds error

commit e354faacd637367ccc7b07874c5807005d95ab5a
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 13:10:51 2025 -0600

    Update Board.java

    This makes it so the pieces stop at the bottom of the board and don't just go off the screen

commit 0d1f6273e0ed4a7eea0851c5a56d5c14833cf5d7
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 12:54:24 2025 -0600

    Update BlockyPanel.java

    fixed gravity

commit 92503fbf639ad01bace09bf2f5721d80f27dcaa2
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 12:53:39 2025 -0600

    Update BlockyGame.java

    Implemented shuffle

commit afac7818201b5bf3a002101d3d20ff403c2dbace
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 12:52:44 2025 -0600

    Update Loader.java

    Whoops, shuffle is NOT supposed to be here

commit e1263a49627b397b498443c05facace832a503f1
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sun Feb 9 12:48:59 2025 -0600

    Update Loader.java

    Implemented shuffle method

commit c364c146b263a2c358629b268a31e5f21ba1c29f
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sat Feb 8 20:16:16 2025 -0600

    Update Loader.java

    fixed off by one error in loader, made program more efficient

commit a731e7a57aaee295f163f5172b1737f27b7f4234
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Sat Feb 8 16:06:48 2025 -0600

    Update Blocky.java

    Changed the locations of the functions so that they weren't nested in main.

commit f60cfe68367c7171c85bdc0eac5b0417735947cf
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Fri Feb 7 12:55:50 2025 -0600

    Update README.md

commit 7c546812d38c6bcc96e91820254ef8d20107c2f1
Author: Emily Lackershire <152826358+emilylackershire@users.noreply.github.com>
Date:   Fri Feb 7 12:53:11 2025 -0600

    Update README.md

commit afe5fb01903e400af8fa786f1a8c10dfa900169f (upstream/main, upstream/HEAD)
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Wed Feb 5 20:51:21 2025 -0600

    Update pom.xml to handle Java versioning

commit 21efa1b57d4ebede094644493aa2c7559c71883b
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Wed Feb 5 09:17:30 2025 -0600

    hey is this how I use git, what is a commit?

commit 567a65a7ce173cf4c0a232aad9c22ab38ca66331
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Wed Feb 5 09:13:20 2025 -0600

    initial commit
(END)