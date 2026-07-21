import { useState } from "react";

export default function SightCard({ sightName }) {
  const test = {
    address: "基隆市七堵區光明路23號",
    category: "歷史古蹟",
    description:
      "七堵前站舊稱「七堵驛」，在1972年七堵調車場啟用之後被分軌並拆為前、後站，屬於前站的七堵驛僅停靠北上至基隆及宜蘭之列車，並設有一座島式月台，後於2007年停用並移交予基隆市文化局管理；而七堵前站的舊站房建於日治時期，雖查無正式的建造年代，但從外觀上可判斷該房舍為和洋混合建築，並有日式屋舍樣式之配置。",
    photoURL:
      "https://photo.travelking.com.tw/scenery/4AFA133B-DAC2-4B19-AE9D-3670B2CDEF85_d.jpg",
    sightName: "七堵鐵道紀念公園(舊七堵前站)",
    zone: "七堵區",
  };

  let name = "";
  if (sightName === "七堵區") {
    name = "qidu";
  } else if (sightName === "中山區") {
    name = "zhongshan";
  } else if (sightName === "中正區") {
    name = "zhongzheng";
  } else if (sightName === "仁愛區") {
    name = "renai";
  } else if (sightName === "安樂區") {
    name = "anle";
  } else if (sightName === "信義區") {
    name = "xinyi";
  } else if (sightName === "暖暖區") {
    name = "nuannuan";
  } else {
    return <p className="text-2xl font-bold text-orange-600">顯示錯誤！</p>;
  } /*
  fetch("http://localhost:8080/api/sights/" + name)
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
    });*/

  const [isOpen, setIsOpen] = useState(false);
  const handleOpen = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="w-full py-1">
      <div className="mx-auto w-11/12 rounded border border-gray-300 bg-orange-50">
        <div className="mx-auto w-11/12 p-3 pt-5">
          <p className="text-2xl">
            {test.sightName}
            <br />
          </p>
          <p className="text-lg">
            <br />
            分類：{test.category}
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
              <img src={test.photoURL} />
              <p className="text-lg">
                {test.description}
                <br />
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
