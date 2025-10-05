import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// mimic backend endpoints used in original project
export const saveFavourite = createAsyncThunk('places/saveFavourite', async (place) => {
  // replace with your backend when available
  const res = await axios.post('http://localhost:8089/places/favourite', place);
  return res.data;
});

export const fetchFavourites = createAsyncThunk('places/fetchFavourites', async () => {
  const res = await axios.get('http://localhost:8089/places/favourite');
  return res.data;
});

export const removeFavourite = createAsyncThunk('places/removeFavourite', async (placeId) => {
  const res = await axios.delete(`http://localhost:8089/places/favourite/${placeId}`);
  return placeId;
});

const placesSlice = createSlice({
  name: 'places',
  // default selected set to KLCC coordinates so map shows KLCC on init
  initialState: { history: [], favourites: [], selected: { name: 'KLCC', lat: 3.15785, lng: 101.7124 } },
  reducers: {
    addSearch(state, action) {
      state.history.push(action.payload);
    },
    selectLocation(state, action) {
      state.selected = action.payload;
    },
    clearHistory(state) {
      state.history = [];
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(saveFavourite.fulfilled, (state, action) => {
        // prefer backend returned object if it contains valid lat/lng; otherwise use original arg
        const payload = action.payload;
        const arg = action.meta.arg;
        const hasCoords = (o) => o && typeof o.lat === 'number' && typeof o.lng === 'number';
        if (hasCoords(payload)) state.favourites.push(payload);
        else if (hasCoords(arg)) state.favourites.push(arg);
        // otherwise ignore
      })
      .addCase(fetchFavourites.fulfilled, (state, action) => {
        state.favourites = action.payload;
      });
    builder.addCase(removeFavourite.fulfilled, (state, action) => {
      const id = action.payload;
      state.favourites = state.favourites.filter(f => (f.placeId || f.id) !== id);
    });
  },
});

export const { addSearch, selectLocation, clearHistory } = placesSlice.actions;
export default placesSlice.reducer;
