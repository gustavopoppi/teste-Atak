import React, { useState } from 'react';
import axios from 'axios';

function Home() {

    const [termoBusca, setTermoBusca] = useState();
    const [resultado, setResultado] = useState([]);

    function handleChange(event) {
        setTermoBusca(event.target.value);
    }

    const handleSubmit = () => {
        axios.get("https://localhost:8080/api/" + termoBusca).then(resultadoRequisicao => {
            setResultado(resultadoRequisicao.data);
        });
    }

    return (
        <div>
            <div className="container p-5">
                <h1>Busca Google</h1>
                <form onSubmit={handleSubmit}>
                    <div className="col-6">
                        <div>
                            <label htmlFor="termoBuscar" className="form-label">Termo a buscar:</label>
                            <input name="termoBuscar" onChange={handleChange} defaultValue={termoBusca} type="text" className="form-control" />
                        </div>
                        <br />
                        <button onClick={handleSubmit} type="button" className="btn btn-primary">Buscar</button>
                    </div>
                </form>
            </div>
            <div className="card-container">
                <h3>{resultado.link}</h3>
                <h3>{resultado.titulo}</h3>
            </div>
        </div>
    )
}

export default Home