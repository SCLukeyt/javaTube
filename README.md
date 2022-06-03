# javaTube

This is a discord bot that gives the first video in the list of videos from a search given by a command, and some statistics on the video

## Why?

This was a school project

##

Libraries Used:
 - Selenium (added 5/10/22 in push r0t1)
 - JDA api (added 5/19/22 in push r0t2)

##

Push logs:
(format: release # take #)

> 5/10/22 - indevVersion r0t1
 - Created Project
 - Created system to take an input of text from a user and give the title of the first video in the search results on youtube for that text input

> 5/19/22 - indevVersion r0t2
 - Created class "scraper"
   - Searches for the input tect on youtube
   - gives back search results and details + link for the target video
 - Added preliminary code (currently commented) for discord integration
   - NOTE: We tried to do the discord stuff first, and it did not go well, please wish us luck in integration (EDIT: integration hell was real)

> 5/24/22 - indevVerision r1t2
  - Full discord integration
    - uses slash command "/scrape" and subsequent options added to the command to fetch information from youtube
    - can give title, channel, view count, relative date posting, video link, and all titles from the search
  - Minor optimization fixes
    - rather than loading youtube home page and waiting for all the images to load, starting page has been set to an empty search page
    - minimized calls to methods where possible
  - Note:
    - The optimization fixes were in today's push, the full integration was the 23rd but I forgot to update the log, hence why this log is take 2, and not take 1 of release 1

##

NOTE:

Depending on decided functionality, bot may become private in order to not get taken down by youtube, and have the same fate as Groovy
