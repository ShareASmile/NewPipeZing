<p align="center"><a href="https://newpipe.net"><img src="https://user-images.githubusercontent.com/15047534/156973787-fdba0c71-b26d-42e0-bb74-55aa8550ddb7.png" width="150"></a></p> 
<h2 align="center"><b>Nord-themed NewPipe</b></h2>
<h4 align="center">An arctic, north-bluish color palette for NewPipe</h4>


## Screenshots

[<img src="https://user-images.githubusercontent.com/15047534/156972893-ba434e72-ae68-48b4-929f-b5c99e40cca9.png" width=160>](https://user-images.githubusercontent.com/15047534/156972893-ba434e72-ae68-48b4-929f-b5c99e40cca9.png)
 [<img src="https://user-images.githubusercontent.com/15047534/156972872-07b6e73d-0627-4c25-90fa-66f55e08371b.png" width=160>](https://user-images.githubusercontent.com/15047534/156972872-07b6e73d-0627-4c25-90fa-66f55e08371b.png)
 [<img src="https://user-images.githubusercontent.com/15047534/156972884-f1f101d3-a9e9-4a7c-99af-d557a83b87b0.png" width=160>](https://user-images.githubusercontent.com/15047534/156972884-f1f101d3-a9e9-4a7c-99af-d557a83b87b0.png)


## Description

NewPipe does not use any Google framework libraries, nor the YouTube API. Websites are only parsed to fetch required info, so this app can be used on devices without Google services installed. Also, you don't need a YouTube account to use NewPipe, which is copylefted libre software.

The only changes you need to make are in the colors_services.xml file inside ```../NewPipe-dev/app/src/main/res/valuesres/values/``` folder

```
<color name="dark_youtube_primary_color">#4C566A</color>
<color name="dark_youtube_dark_color">#3B4252</color>
<color name="dark youtube accent color">#FFFFFF</color>
<color name="dark youtube statusbar color">#4C566A</color>
```

<!-- Hidden span to keep old links compatible. -->
<span id="updates"></span>

## Installation and updates
You can install customized NewPipe using the following method:
Build a debug APK yourself. This is the fastest way to get new features on your device.

In the meanwhile, if you want to switch sources for some reason (e.g. NewPipe's core functionality breaks and F-Droid doesn't have the latest update yet), we recommend following this procedure:
1. Back up your data via Settings > Content > Export Database so you keep your history, subscriptions, and playlists
2. Uninstall NewPipe
3. Download the APK from the new source and install it
4. Import the data from step 1 via Settings > Content > Import Database

## License
[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](https://www.gnu.org/licenses/gpl-3.0.en.html)  

NewPipe is Free Software: You can use, study, share, and improve it at
will. Specifically you can redistribute and/or modify it under the terms of the
[GNU General Public License](https://www.gnu.org/licenses/gpl.html) as
published by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.  

