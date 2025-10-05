import React from 'react';
import { createPortal } from 'react-dom';

export default function ConfirmModal({ open, title = 'Confirm', message = 'Are you sure?', onConfirm, onCancel }) {
  if (!open) return null;

  const overlayStyle = { position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.5)', zIndex: 2147483646 };
  const modalStyle = { position: 'fixed', zIndex: 2147483647, maxWidth: '520px', width: '90%', left: '50%', top: '50%', transform: 'translate(-50%, -50%)' };

  const node = (
    <div>
      <div style={overlayStyle} onClick={onCancel} />
      <div style={modalStyle} className="bg-white rounded shadow-lg p-4">
        <h3 className="text-lg font-semibold mb-2">{title}</h3>
        <p className="mb-4">{message}</p>
        <div className="flex justify-end gap-2">
          <button className="px-3 py-1 border rounded" onClick={onCancel}>Cancel</button>
          <button className="px-3 py-1 bg-red-600 text-white rounded" onClick={onConfirm}>Delete</button>
        </div>
      </div>
    </div>
  );

  return createPortal(node, document.body);
}
