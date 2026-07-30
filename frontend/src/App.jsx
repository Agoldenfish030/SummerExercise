import { useEffect, useState } from "react";
import "./App.css";

import SightButton from "./components/RequestSight/SightButton";
import SightCards from "./components/RequestSight/SightCards";

async function getData(name) {
  try {
    const res = await fetch("/api/sights/" + name);
    if (!res.ok) {
      console.error("mongodb資料獲取失敗。");
    }
    const data = await res.json();
    return data;
  } catch (error) {
    console.error("Fetch error: " + error);
    return [];
  }
}

function App() {
  const zoneList = [
    "七堵區",
    "中山區",
    "中正區",
    "仁愛區",
    "安樂區",
    "信義區",
    "暖暖區",
  ];
  const zoneNameToEng = [
    ["七堵區", "qidu"],
    ["中山區", "zhongshan"],
    ["中正區", "zhongzheng"],
    ["仁愛區", "renai"],
    ["安樂區", "anle"],
    ["信義區", "xinyi"],
    ["暖暖區", "nuannuan"],
  ];
  const [sightsList, setSightsList] = useState({});
  const [selectedZones, setSelectedZones] = useState([]);
  const [loading, setLoading] = useState(true);
  const handleSelectedZones = (zone) => {
    if (selectedZones.includes(zone)) {
      setSelectedZones(selectedZones.filter((sight) => sight !== zone));
    } else {
      setSelectedZones([...selectedZones, zone]);
    }
  };

  useEffect(() => {
    const fetchAllSights = async () => {
      const promises = zoneNameToEng.map(async ([zhZone, engZone]) => {
        const data = await getData(engZone);
        return [zhZone, data];
      });
      Promise.all(promises).then((cardsList) => {
        setSightsList(Object.fromEntries(cardsList));
        setLoading(false);
      });
    };

    fetchAllSights();
  }, []);

  const allSelectedSights = selectedZones.flatMap((zone) => sightsList[zone]);

  return (
    <>
      <div
        className={`absolute z-40 h-full w-full bg-gray-400 opacity-50 ${
          loading ? "visible" : "invisible"
        }`}
      ></div>
      <div
        className={`${loading ? "visible" : "invisible"} relative z-50 h-full w-full`}
      >
        <div className="absolute top-52 z-50 w-full">
          <p className="text-center text-5xl">Loading...</p>
        </div>
      </div>
      <div className="grid w-full grid-cols-7 border-0 border-b border-gray-300 max-lg:grid-cols-1">
        {zoneList.map((name) => (
          <SightButton
            key={name}
            zone={name}
            setSelected={() => handleSelectedZones(name)}
          />
        ))}
      </div>
      <div className="grid w-full grid-cols-3 max-lg:grid-cols-1">
        <SightCards key="cards" sights={allSelectedSights} />
      </div>
    </>
  );
}

export default App;
