import { v4 as uuidv4 } from 'uuid'
import { useEffect, useState } from 'react';
import { useLoaderData } from 'react-router-dom';
import './HotelDetailPage.css';

export async function loader({ params }) {
    console.log(params.id);
    return params.id
}

function HotelRow({ name, id }) {
    return (
        <p data-id={id}>
            {name}
        </p>
    );
}

function HotelDetailPage() {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const hotelId = useLoaderData();

    useEffect(() => {
        const sessionId = uuidv4();
        const uri = "http://localhost:8080/properties/" + hotelId;
        fetch(uri)
            .then(res => res.json())
            .then(
                (result) => {
                    setLoading(false);
                    setData(result);
                },
                (error) => {
                    setLoading(false);
                    setError(error);
                });
        
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                eventType: 'Start',
                medium: 'web' 
            })
        };
        const eventUri = `http://localhost:8081/property/${hotelId}/events/${sessionId}`;
        fetch(eventUri, requestOptions);
    }, [hotelId]);

    return (
        <>
            <div className="App">
                <header className="App-header">
                    <div>
                        {loading ? (
                            <p>Loading hotel...</p>
                        ) : error ? (
                            <p>There seems to be a problem, please try again later!</p>
                        ) : (
                            <HotelRow name={data.name} id={data.id} />
                        )}
                    </div>
                </header>
            </div>
        </>
    );
}

export default HotelDetailPage;
