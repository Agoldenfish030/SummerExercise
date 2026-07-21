import { use, useState } from "react";
import "./App.css";

import SightButton from "./components/RequestSight/SightButton";
import SightCard from "./components/RequestSight/SightCard";

function App() {
  const [selectedSights, setSelectedSights] = useState([]);

  const handleSelectedSights = (sightName) => {
    if (selectedSights.includes(sightName)) {
      setSelectedSights(selectedSights.filter((sight) => sight !== sightName));
    } else {
      setSelectedSights([...selectedSights, sightName]);
    }
  };

  return (
    <>
      <div className="grid w-full grid-cols-7 border-0 border-b border-gray-300 max-lg:grid-cols-1">
        {[
          "七堵區",
          "中山區",
          "中正區",
          "仁愛區",
          "安樂區",
          "信義區",
          "暖暖區",
        ].map((name) => (
          <SightButton
            sightName={name}
            setSelected={() => handleSelectedSights(name)}
          />
        ))}
      </div>
      <div className="grid w-full grid-cols-3 p-1 max-lg:grid-cols-1">
        {selectedSights.map((name) => (
          <SightCard sightName={name} />
        ))}
      </div>
    </>
  );
}

export default App;
