import { useState } from "react";

function SingleCard({ sight }) {
  const [photoUrl, setPhotoUrl] = useState(sight.photoURL);
  const [isOpen, setIsOpen] = useState(false);
  const handleData = () => {
    setIsOpen(!isOpen);
  };
  const handleMissing = () => {
    setPhotoUrl(sight.fallbackPhoto);
  };
  const handleAddr = (sightName) => {
    const query = encodeURIComponent(`基隆市 ${sightName}`);
    const url = `https://www.google.com/maps/search/?api=1&query=${query}`;
    window.open(url, "_blank");
  };
  const haveImg = Boolean(
    sight.photoURL &&
    sight.photoURL !== "" &&
    sight.fallbackPhoto &&
    sight.fallbackPhoto !== "",
  );

  return (
    <div className="p-2">
      <div className="mx-auto w-11/12 rounded border border-gray-300 bg-orange-50">
        <div className="mx-auto w-11/12 p-3 pt-5">
          <p className="text-2xl">
            {sight.sightName}
            <br />
          </p>
          <p className="pt-1 text-lg">
            分類：{sight.category}
            <br />
            地區：{sight.zone}
            <br />
            <br />
          </p>
          <div className="flex flex-row space-x-1">
            <button
              className="rounded border border-blue-500 bg-white p-1 text-2xl text-blue-500 hover:bg-blue-500 hover:text-white"
              onClick={() => handleAddr(sight.sightName)}
            >
              地址
            </button>
            <button
              className="rounded border border-blue-500 bg-white p-1 text-2xl text-blue-500 hover:bg-blue-500 hover:text-white"
              onClick={handleData}
            >
              詳細資訊
            </button>
          </div>
          <div
            className={`grid pt-2 transition-all duration-400 ease-in-out ${
              isOpen ? "grid-rows-[1fr]" : "grid-rows-[0fr]"
            }`}
          >
            <div className="overflow-hidden">
              {haveImg && (
                <img
                  src={photoUrl}
                  alt="該區景點照片"
                  onError={handleMissing}
                />
              )}
              <p className="text-lg">
                {sight.description}
                <br />
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default function SightCards({ sights }) {
  if (!sights || sights.length === 0) return;

  const columns = [[], [], []];
  sights.forEach((sight, index) => {
    columns[index % 3].push(sight);
  });

  return (
    <>
      {columns.map((colSights, colIndex) => (
        <div key={colIndex} className="flex flex-col">
          {colSights.map((sight) => (
            <SingleCard key={sight.sightName} sight={sight} />
          ))}
        </div>
      ))}
    </>
  );
}
