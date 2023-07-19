import React, { useState } from 'react';

function Home() {

    const [termoBuscar, setProduto] = useState({nome:'', preco:'', quantidade:'', imagem:''});

    function handleChange(event){
        setProduto({...produto,[event.target.name]:event.target.value});
    }

    const handleSubmit = (data) => {
        produto.nome = data.nome;
        produto.preco = data.preco;
        produto.quantidade = data.quantidade;
        produto.imagem = 'imagem'/*data.imagem[0].name*/
        
        axios.post('http://localhost:8080/produtos', produto).then(result => {
            navigate('/');
        });
    }

    return (
        <div>
            <form>
                <div class="form-row align-items-center">
                    <div class="col-sm-3">
                        <label class="sr-only" for="termoBusca">Digite termo a buscar:</label>
                        <input type="text" class="form-control" id="termoBusca" placeholder="" />
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>
            <br />
            <form class="card">
                <div class="card-body">
                    <div class="col-sm-3 p-2">
                        <label class="sr-only" for="termoBusca">Digite termo a buscar:</label>
                        <input type="text" class="form-control" id="termoBusca" placeholder="" />
                    </div>
                    <div class="col-auto p-2">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </div>
            </form>

            <div className="container p-5">
                <h1>Busca Google</h1>
                <form >
                    <div className="col-6">
                        <div>
                            <label for="termoBuscar" className="form-label">Termo a buscar:</label>
                            <input onChange={handleChange} defaultValue={produto.nome} type="text" className="form-control" />
                            
                        </div>                        
                        <br />
                        {/* <input type='submit' value="Buscar" className='btn btn-primary'></input> */}
                        <button type="submit" class="btn btn-primary">Buscar</button>

                    </div>
                </form>
            </div>
        </div>


    )
}

export default Home