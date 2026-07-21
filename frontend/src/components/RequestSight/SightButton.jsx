export default function SightButton({ sightName, setSelected }) {
  return (
    <button className="mx-auto w-4/5 cursor-pointer py-3" onClick={setSelected}>
      <p className="rounded bg-blue-200 py-3 text-center text-3xl shadow hover:bg-blue-300">
        {sightName}
      </p>
    </button>
  );
}
