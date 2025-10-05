import React, { useState, useRef } from 'react';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import { addSearch, saveFavourite, selectLocation } from '../redux/placesSlice';

function debounce(fn, wait) {
  let t;
  return (...args) => {
    clearTimeout(t);
    t = setTimeout(() => fn(...args), wait);
  };
}

export default function SearchBox() {
  const dispatch = useDispatch();
  const history = useSelector((s) => s.places.history);
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const controllerRef = useRef(null);

  async function fetchSuggestions(q) {
    if (!q) return setSuggestions([]);
    if (controllerRef.current) controllerRef.current.abort();
    controllerRef.current = new AbortController();

    try {
      const url = `https://nominatim.openstreetmap.org/search?format=jsonv2&q=${encodeURIComponent(q)}&addressdetails=1&limit=5`;
      const res = await axios.get(url, { signal: controllerRef.current.signal });
      setSuggestions(res.data.map((item) => ({
        placeId: item.place_id,
        name: item.display_name,
        lat: parseFloat(item.lat),
        lng: parseFloat(item.lon),
        raw: item,
      })));
    } catch (err) {
      if (err.name !== 'CanceledError') console.error(err);
    }
  }

  const debounced = useRef(debounce(fetchSuggestions, 300)).current;

  function onChange(e) {
    const v = e.target.value;
    setQuery(v);
    debounced(v);
  }

  function selectPlace(place) {
    dispatch(addSearch(place));
    dispatch(selectLocation(place));
    // optionally save favourite
    // dispatch(saveFavourite(place));
  }

  function onSaveFavourite(place) {
    const toSave = {
      placeId: place.placeId,
      name: place.name,
      address: place.raw?.display_name || place.address || null,
      lat: place.lat,
      lng: place.lng,
    };
    if (typeof toSave.lat !== 'number' || typeof toSave.lng !== 'number') {
      console.warn('Cannot save favourite without numeric lat/lng', toSave);
      return;
    }
    dispatch(saveFavourite(toSave));
  }

  return (
    <div>
      <input value={query} onChange={onChange} placeholder="Search places..." className="w-full p-2 border rounded" />
      <div className="mt-2 border rounded overflow-auto max-h-40">
        {suggestions.map((s) => (
          <div key={s.placeId} className="flex items-center justify-between p-2 border-b hover:bg-gray-50">
            <div className="mr-2 flex-1" onClick={() => selectPlace(s)}>{s.name}</div>
            <button className="px-2 py-1 bg-green-600 text-white rounded" onClick={() => onSaveFavourite(s)}>Save</button>
          </div>
        ))}
      </div>

      <h3 className="mt-4 font-semibold">Search History</h3>
      <div className="mt-2 border rounded overflow-auto max-h-48">
        {history.slice().reverse().map((h, i) => (
          <div key={`${h.placeId}-${i}`} className="p-2 border-b">{h.name}</div>
        ))}
      </div>
    </div>
  );
}
