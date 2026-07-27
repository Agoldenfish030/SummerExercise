import { useEffect, useState } from "react";

async function getData({ name }) {
  try {
    const res = await fetch("/api/sights/" + name);
    const data = await res.json();
    return data;
  } catch (error) {
    console.error("Fetch error: " + error);
    return [];
  }
}

function SingleCard({ sight }) {
  const [isOpen, setIsOpen] = useState(false);
  const handleOpen = () => {
    setIsOpen(!isOpen);
  };

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
              <img src={sight.photoURL} />
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
  let name = "";
  if (zone === "七堵區") {
    name = "qidu";
  } else if (zone === "中山區") {
    name = "zhongshan";
  } else if (zone === "中正區") {
    name = "zhongzheng";
  } else if (zone === "仁愛區") {
    name = "renai";
  } else if (zone === "安樂區") {
    name = "anle";
  } else if (zone === "信義區") {
    name = "xinyi";
  } else if (zone === "暖暖區") {
    name = "nuannuan";
  } else {
    return <p className="text-2xl font-bold text-orange-600">顯示錯誤！</p>;
  }

  const [data, setData] = useState([]);
  useEffect(() => {
    getData({ name }).then((resData) => {
      setData(resData || []);
    });
  }, []);

  return (
    <>
      {data.map((sight) => (
        <SingleCard sight={sight} />
      ))}
    </>
  );
}
