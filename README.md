# phpbbcrawler
Link crawler for a phpBB forum used for archiving https://forum.mozilla.cz/.

Before archiving, check if your phpBB instance adds `sid` to the query string. If so, apply [this change](https://www.phpbb.com/community/viewtopic.php?f=46&t=587415#p3236785) before running this tool, as web.archive.org will treat URL with different `sid` as completely different pages. Than you can use [something like this](https://github.com/MozillaCZ/forum-mozilla-cz/) to replace your forum and link users to the archive.
