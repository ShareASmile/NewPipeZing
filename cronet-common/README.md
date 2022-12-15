## `cronet-common` module

An edited Cronet `cronet-common` module which prevents sending the name of the application using Cronet in the QUIC User-Agent ID.

### Why this module has been created and the original one is not used directly?

Cronet does not allow editing the QUIC User-Agent ID, as the method which generates this User-Agent ID is package-private and the method's class is final.
In order to NewPipe to be not fingerprinted by YouTube with this User-Agent ID, we remove the package name in the QUIC User-Agent ID and keep only the Cronet part.

### Update the module with newest Cronet versions

Use the following instructions:

1. Download the sources and the compiled classes corresponding to the newest `cronet-common` module from Google's Maven: go to https://maven.google.com/web/index.html#org.chromium.net:cronet-common:CRONET-VERSION and download the `source` and `aar` artifacts. Replace `CRONET-VERSION` by the Cronet version with which you want to update this module. 
2. Open the sources JAR as an archive.
3. Extract the archive contents.
4. Replace the old classes of the module with the newest ones;
5. Compare the files of the sources JAR and the compiled ones. To do so, use the following steps:
   - Open the AAR file downloaded at the step 1 as an archive, extract the `classes.jar` file and open this as an archive.
   - Compare the classes in this file and the ones in the source archive.
   - If you find missing classes in the source files, you must add them manually. See the section "Add missing source files" for more details.
6. Look where the QUIC User-Agent ID is generated and apply the required changes to only use the Cronet part. The changes should be normally done on the method `getQuicUserAgentIdFrom` of the `UserAgent` class.
7. In the `build.gradle` file **of the project (not the app one!)**, update the Cronet version.
8. Test the changes. You must check the changes in the app on a debug build and a release app build (add a suffix to the release Gradle build using the `packageSuffix` property to not overwrite the real release) to ensure that no file is missing or no compile error from Cronet is present.
9. Fix the potential build errors, regressions or new bugs.
10. Congratulations! You should have updated successfully this module.

### Add missing source files

Some classes may have their source file missing in the Cronet common sources archive. In this case, you must add them manually.

1. If missing classes are classes which are not already in the module:
   - Create the missing packages in the module, if required;
   - Create dummy classes corresponding in the module to the missing classes.
2. Go to https://source.chromium.org/chromium/chromium/src.
3. Search for a missing class.
4. Open its corresponding source in the website.
5. Use the version of the file corresponding to the release of Cronet: click on the arrow after the branch name (which should be `main`), select `Tag` in the popup which opened, then enter the Cronet version but **append `.0`** after the release major version (because you will have to use Chromium's release versioning and not the Cronet one): for instance, Cronet version `108.5359.79` becomes Chromium version `108.0.5359.79`.
6. Select all the file content and copy this content into your clipboard. Note that some classes are generated from templates, so you will have to decompile the .class version of the file to use the correct property.
7. Go to the dummy class and paste the content of your clipboard.
8. Check if the copy of the file is correct. Especially, check that blank lines have been properly added and if a newline at the end of file is present.
9. Correct the module file to look exactly like the original, if needed.
10. Repeat these instructions from step 3 for all missing classes.
