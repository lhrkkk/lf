# labkit frontend
The [labkit](https://github.com/lhrkkk/labkit) platforms' frontend.

lf is short for "labkit frontend", also the project name.

## Usage

This is the frontend part of labkit, you can browse it on our labkit website. which is coming.

## Developement

1. Preparation

  Install [Leiningen](http://leiningen.org/)  (plus Java) and, clone this repo
   ```
   git clone  https://github.com/lhrkkk/labkit

   ```

2. Build
   ```
   lein clean && lein figwheel
   ```
3. Run
   ```
   open http://localhost:3450
   ```

## compile a production version

1. Compile
   ```
   lein do clean, with-profile prod compile
   ```

2. Open the following in your browser
   ```
   resources/public/index.html
   ```
