import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchFavourites, selectLocation, removeFavourite } from '../redux/placesSlice';
import ConfirmModal from './ConfirmModal';

export default function FavouritesList() {
  const dispatch = useDispatch();
  const favs = useSelector((s) => s.places.favourites);
  const [deletingId, setDeletingId] = useState(null);

  useEffect(() => {
    dispatch(fetchFavourites());
  }, [dispatch]);

  function confirmDelete(id) {
    setDeletingId(id);
  }

  function doDelete() {
    if (!deletingId) return;
    dispatch(removeFavourite(deletingId));
    setDeletingId(null);
  }

  return (
    <div className="mt-4">
      <h3 className="font-semibold">Favourites</h3>
      <div className="mt-2 border rounded overflow-auto max-h-40">
        {favs && favs.length ? favs.map((f) => (
          <div key={f.id || f.placeId} className="p-2 border-b flex items-center justify-between">
            <div className="cursor-pointer flex-1" onClick={() => dispatch(selectLocation(f))}>{f.name || f.display_name}</div>
            <button className="ml-2 px-2 py-1 bg-red-600 text-white rounded" onClick={() => confirmDelete(f.placeId || f.id)}>Delete</button>
          </div>
        )) : <div className="p-2">No favourites</div>}
      </div>

      <ConfirmModal open={!!deletingId} title="Delete Favourite" message="Are you sure you want to delete this favourite?" onConfirm={doDelete} onCancel={() => setDeletingId(null)} />
    </div>
  );
}
