import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './App.css';

function HotelRow({ name, id }) {
  return (
    <Link to={`/hotel/${id}`} style={{ textDecoration: 'none', color: 'antiquewhite' }}>
      <p key={id}>
        {name}
      </p>
    </Link>
  );
}

function App() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/properties/")
      .then(res => res.json())
      .then(
        (result) => {
          setLoading(false);
          setData(result);
        },
        (error) => {
          setLoading(false);
          setError(error);
        })
  }, []);

  return (
    <>
      <div className="App">
        <header className="App-header">
          <div>
            {loading ? (
              <p>Loading hotels...</p>
            ) : error ? (
              <p>There seems to be a problem, please try again later!</p>
            ) : (
              data.map((props) => {
                return (
                  <HotelRow key={props.id} name={props.name} id={props.id} />
                );
              })
            )}
          </div>
        </header>
      </div>
    </>
  );
}

export default App;
