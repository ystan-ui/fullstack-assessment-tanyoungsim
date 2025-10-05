# react-places (Nominatim + Leaflet demo)

This is a small demo app that implements place search/autocomplete using OpenStreetMap's Nominatim API and a Leaflet map. It mirrors the data model used in the `react-google-places` project and stores search history in Redux.

Quick start

1. cd into the project:

```powershell
cd d:\YS_FILE\workspace\simple_apps\frontend_place_app\react-places
```

2. Install dependencies:

```powershell
npm install
```

3. Start the dev server:

```powershell
npm start
```

Notes

- The app does not require an API key (uses Nominatim). Nominatim has usage policies and rate limits: https://operations.osmfoundation.org/policies/nominatim/ - for production use consider running your own instance or using a commercial geocoding provider.
- The Redux slice includes `saveFavourite`/`fetchFavourites` thunks which point to `http://localhost:8089/places/favourite`. You can implement the backend separately (sample Spring Boot API can be provided).
- To integrate with your existing `react-google-places` backend, point the axios URLs to that backend base.
