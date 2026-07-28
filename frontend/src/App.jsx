import { useEffect, useState } from "react";
import "./App.css";

import SightButton from "./components/RequestSight/SightButton";
import SightCard from "./components/RequestSight/SightCard";

async function getData(name) {
  try {
    console.log("準備進行取資：" + name);
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
  const zoneNameToEng = {
    七堵區: "qidu",
    中山區: "zhongshan",
    中正區: "zhongzheng",
    仁愛區: "renai",
    安樂區: "anle",
    信義區: "xinyi",
    暖暖區: "nuannuan",
  };
  const [sightsList, setSightsList] = useState({});
  const [selectedSights, setSelectedSights] = useState([]);
  const handleSelectedZones = (zone) => {
    if (selectedSights.includes(zone)) {
      setSelectedSights(selectedSights.filter((sight) => sight !== zone));
    } else {
      setSelectedSights([...selectedSights, zone]);
    }
  };

  useEffect(() => {
    const fetchAllSights = async () => {
      const entries = Object.entries(zoneNameToEng);
      const promises = entries.map(async ([zhZone, engZone]) => {
        const data = await getData(engZone);
        return [zhZone, data];
      });
      Promise.all(promises).then((cardsList) => {
        setSightsList(Object.fromEntries(cardsList));
      });
    };

    fetchAllSights();
  }, []);

  return (
    <>
      <div className="grid w-full grid-cols-7 border-0 border-b border-gray-300 max-lg:grid-cols-1">
        {zoneList.map((name) => (
          <SightButton
            key={name}
            zone={name}
            setSelected={() => handleSelectedZones(name)}
          />
        ))}
      </div>
      <div className="grid w-full grid-cols-3 p-1 max-lg:grid-cols-1">
        {selectedSights.map((name) => (
          <SightCard key={name} zone={sightsList[name]} />
        ))}
      </div>
    </>
  );
}

export default App;
