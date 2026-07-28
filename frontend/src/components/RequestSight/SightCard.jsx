import { useEffect, useState } from "react";

function SingleCard({ sight }) {
  console.log("建立SingleCard：" + sight.sightName);
  const [isOpen, setIsOpen] = useState(false);
  const handleOpen = () => {
    setIsOpen(!isOpen);
  };
  const haveImg = Boolean(sight.photoURL && sight.photoURL !== "");

  return (
    <div className="p-2">
      <div className="mx-auto w-11/12 rounded border border-gray-300 bg-orange-50">
        <div className="mx-auto w-11/12 p-3 pt-5">
          <p className="text-2xl">
            {sight.sightName}
            <br />
          </p>
          <p className="text-lg">
            <br />
            分類：{sight.category}
            <br />
            地區：{sight.zone}
            <br />
            <br />
          </p>
          <button
            className="rounded border border-blue-500 bg-white p-1 text-2xl text-blue-500 hover:bg-blue-500 hover:text-white"
            onClick={handleOpen}
          >
            詳細資訊
          </button>
          <div
            className={`grid pt-2 transition-all duration-400 ease-in-out ${
              isOpen ? "grid-rows-[1fr]" : "grid-rows-[0fr]"
            }`}
          >
            <div className="overflow-hidden">
              {haveImg && <img src={sight.photoURL} alt="該區景點照片" />}
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

export default function SightCard({ zone }) {
  return (
    <>
      {zone.map((sight) => (
        <SingleCard key={sight.sightName} sight={sight} />
      ))}
    </>
  );
}
