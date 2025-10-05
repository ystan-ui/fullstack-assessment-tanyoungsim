import React from 'react';
import SearchBox from './components/SearchBox';
import MapDisplay from './components/MapDisplay';
import FavouritesList from './components/FavouritesList';
import './index.css';
import { useDispatch } from 'react-redux';
import { selectLocation } from './redux/placesSlice';

export default function App() {
  const dispatch = useDispatch();
  return (
    <div className="p-6 font-sans">
      <h1 className="text-2xl font-bold mb-4">React Places (Nominatim + Leaflet)</h1>
      <div className="flex gap-4">
        <div className="w-80">
          <SearchBox />
          <FavouritesList />
          <div className="mt-3">
            <button className="px-3 py-1 bg-blue-600 text-white rounded" onClick={() => dispatch(selectLocation({ name: 'KLCC', lat: 3.15785, lng: 101.7124 }))}>Go to KLCC (Malaysia)</button>
          </div>
        </div>
        <div className="flex-1 h-[600px]">
          <MapDisplay />
        </div>
      </div>
    </div>
  );
}
