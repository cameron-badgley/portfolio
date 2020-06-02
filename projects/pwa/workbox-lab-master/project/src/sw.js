/*
Copyright 2018 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
importScripts('https://storage.googleapis.com/workbox-cdn/releases/3.5.0/workbox-sw.js');

if (workbox) {
  console.log(`Yay! Workbox is loaded 🎉`);

  workbox.precaching.precacheAndRoute([]);

  workbox.routing.registerRoute(
    /(.*)articles(.*)\.(?:png|gif|jpg)/,
    workbox.strategies.cacheFirst({
      cacheName: 'images-cache',
      plugins: [
        new workbox.expiration.Plugin({
          maxEntries: 50,
          maxAgeSeconds: 30 * 24 * 60 * 60, // 30 Days
        })
      ]
    })
  );

  /*
  Optional: Write your own route that caches the user avatar. The route should match requests 
  to /images/icon/* and handle the request/response using the staleWhileRevalidate strategy. 
  Give the cache the name "icon-cache" and allow a maximum of five entries to be stored in the 
  cache. This strategy is good for icons and user avatars that change frequently but the latest versions are not essential to the user experience. You'll need to remove the icon from the precache manifest so that the service worker uses your staleWhileRevalidate route instead of the implicit cache-first route established by the precache method.
  */
 workbox.routing.registerRoute(
  /(.*)icon(.*)\.(?:png|gif|jpg|svg)/,
  workbox.strategies.cacheFirst({
    cacheName: 'icon-cache',
    plugins: [
      new workbox.expiration.Plugin({
        maxEntries: 5,
        maxAgeSeconds: 30 * 24 * 60 * 60, // 30 Days
      })
    ]
  })
);



} else {
  console.log(`Boo! Workbox didn't load 😬`);
}