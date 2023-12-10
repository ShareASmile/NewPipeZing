# FoxPipe

A NewPipe fork with the old UI having separate Players handling 3 seperate Queues
<p align="center"><a href="https://github.com/ShareASmile/FoxPipe"><img src="assets/new_pipe_icon_5.png" width="150"></a></p> 
<h2 align="center"><b>FoxPipe</b></h2>
<h4 align="center">A libre lightweight streaming frontend for Android.</h4>

<p align="center">
<a href="https://github.com/ShareASmile/FoxPipe/releases" alt="GitHub release"><img src="https://img.shields.io/github/release/ShareASmile/FoxPipe.svg" ></a>
<a href="https://www.gnu.org/licenses/gpl-3.0" alt="License: GPLv3"><img src="https://img.shields.io/badge/License-GPL%20v3-blue.svg"></a>
<a href="https://github.com/ShareASmile/FoxPipe/actions" alt="Build Status"><img src="https://github.com/ShareASmile/FoxPipe/workflows/CI/badge.svg?branch=master&event=push"></a>
</p>
<hr>
<p align="center"><a href="#screenshots">Screenshots</a> &bull; <a href="#description">Description</a> &bull; <a href="#features">Features</a> &bull; <a href="#installation-and-updates">Installation and updates</a> &bull; <a href="#contribution">Contribution</a> &bull; <a href="#license">License</a></p>
<p align="center"><a href="https://github.com/ShareASmile/FoxPipe/">Website</a> &bull; <a href="https://newpipe.net/FAQ/">FAQ</a> &bull; <a href="https://newpipe.net/press/">Press</a></p>
<hr>

<b>WARNING: THIS IS A BETA VERSION, THEREFORE YOU MAY ENCOUNTER BUGS. IF YOU DO, OPEN AN ISSUE VIA OUR GITHUB REPOSITORY.</b>

<b>PUTTING NEWPIPE OR ANY FORK OF IT INTO THE GOOGLE PLAY STORE VIOLATES THEIR TERMS AND CONDITIONS.</b>

## Screenshots

[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_01.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_01.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_02.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_02.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_03.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_03.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_04.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_04.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_05.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_05.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_07.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_07.png)
[<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/shot_10.png" width=160>](fastlane/metadata/android/en-US/images/phoneScreenshots/shot_10.png)
[<img src="fastlane/metadata/android/en-US/images/tenInchScreenshots/shot_11.png" width=405>](fastlane/metadata/android/en-US/images/tenInchScreenshots/shot_11.png)
[<img src="fastlane/metadata/android/en-US/images/tenInchScreenshots/shot_12.png" width=405>](fastlane/metadata/android/en-US/images/tenInchScreenshots/shot_12.png)


## Description
This repository contains old preunified version 0.19.8 of [NewPipe](https://github.com/TeamNewPipe/NewPipe/releases/tag/v0.19.8) with legacy version of [NewPipeExtractor](https://github.com/ShareASmile/NewPipeExtractor) dependency.

The application itself heavily relies on the extractor component which is responsible for proper parsing of various video/audio streams, including Youtube site. The old NewPipe version 0.19.8 depends on old extractor version which is practically deprecated and can't handle current Youtube (and similar?) streams,thus rendering the application useless for daily use.
FoxPipe in this repository uses the updated version of NewPipeExtractor for legacy devices and resolves the forementioned issue, thus making it possible to use old NewPipe version 0.19.8 based FoxPipe with some bug fixes & features added along with updated extractor version. You don't need a YouTube account to use NewPipe, it is a copylefted libre software.

## Motivation

Not so long ago, NewPipe project implemented a new UI elements for video streams. Personally, I didn't like that change. I wanted to keep using the old UI having separate Players(Video, Popup & Background) handling three seperate Queues simultaneously instead.

### Features

* Search videos
* Display general info about videos
* Watch YouTube videos
* Listen to YouTube videos
* Popup mode (floating player)
* Select streaming player to watch video with
* Make Personalised Watch Later Playlists
* Open a video in Kodi
* Show next/related videos
* Search YouTube in a specific language
* Watch/Block age restricted material
* Display general info about channels
* Search channels
* Watch videos from a channel
* Orbot/Tor support (not yet directly)
* 1080p/2K/4K support
* View history
* Subscribe to channels
* Search history
* Search/watch playlists
* Watch as enqueued playlists
* Enqueue videos
* Local playlists
* Subtitles
* Livestream support
* Show comments

### Supported Services

NewPipe supports multiple services. Our [docs](https://teamnewpipe.github.io/documentation/) provide more info on how a new service can be added to the app and the extractor. Please get in touch with us if you intend to add a new one. Currently supported services are:

* YouTube
* SoundCloud \[beta\]
* media.ccc.de \[beta\]
* PeerTube instances \[beta\]
* Bandcamp \[beta\]

<!-- Hidden span to keep old links compatible. -->
<span id="updates"></span>

## Installation and updates
You can install NewPipe using one of the following methods:
 1. Download the APK from [Github Releases](https://github.com/ShareASmile/FoxPipe/releases) and install it.
 2. Build a debug APK yourself or install from [actions](https://github.com/ShareASmile/FoxPipe/actions) This is the fastest way to get new features on your device.

We recommend method 1 for most users. 2. Building a debug APK using method 2 excludes a key entirely. Signing keys help ensure that a user isn't tricked into installing a malicious update to an app.

## Contribution
Whether you have ideas, translations, design changes, code cleaning, or real heavy code changes, help is always welcome.
The more is done the better it gets!

If you'd like to get involved, check our [contribution notes](.github/CONTRIBUTING.md).


## Privacy Policy

The FoxPipe project aims to provide a private, anonymous experience for using media web services.
Therefore, the app does not collect any data without your consent.

## License
[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](https://www.gnu.org/licenses/gpl-3.0.en.html)  

NewPipe is Free Software: You can use, study share and improve it at your
will. Specifically you can redistribute and/or modify it under the terms of the
[GNU General Public License](https://www.gnu.org/licenses/gpl.html) as
published by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
