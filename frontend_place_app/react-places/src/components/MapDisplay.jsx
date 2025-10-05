import React from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import { useSelector } from 'react-redux';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';

// fix default icon path issues in many setups
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
});

const blueIcon = new L.Icon({
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  iconSize: [25, 41],
  className: 'marker-blue'
});

export default function MapDisplay() {
  const history = useSelector((s) => s.places.history);
  const selected = useSelector((s) => s.places.selected);
  const favourites = useSelector((s) => s.places.favourites);
  const centre = history.length ? [history[history.length-1].lat, history[history.length-1].lng] : [51.505, -0.09];

  return (
    <div className="h-full w-full">
      <MapContainer center={centre} zoom={13} style={{ height: '100%', width: '100%' }}>
      <TileLayer
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <MapPanner selected={selected} />
      {history.map((h) => (
        <Marker key={`${h.placeId}-${h.lat}-${h.lng}`} position={[h.lat, h.lng]}>
          <Popup>{h.name}</Popup>
        </Marker>
      ))}
      {selected && typeof selected.lat === 'number' && typeof selected.lng === 'number' && (
        <Marker position={[selected.lat, selected.lng]} icon={blueIcon}>
          <Popup>{selected.name}</Popup>
        </Marker>
      )}
      {favourites && favourites.filter(f => typeof f.lat === 'number' && typeof f.lng === 'number').map((f) => (
        <Marker key={`fav-${f.id || f.placeId}`} position={[f.lat, f.lng]}>
          <Popup>{f.name || f.display_name}</Popup>
        </Marker>
      ))}
      </MapContainer>
    </div>
  );
}

function MapPanner({ selected }) {
  const map = useMap();
  React.useEffect(() => {
    if (!selected) return;
    if (typeof selected.lat === 'number' && typeof selected.lng === 'number') {
      map.setView([selected.lat, selected.lng], 14);
    }
  }, [selected, map]);
  return null;
}
