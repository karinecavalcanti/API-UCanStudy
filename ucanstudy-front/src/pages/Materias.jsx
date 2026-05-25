import { useState, useEffect } from 'react'
import axios from 'axios'

function Materias() {
  const [materias, setMaterias] = useState([])
  const [nomeMateria, setNomeMateria] = useState('')
  const [idUsuario, setIdUsuario] = useState('')

  useEffect(() => {
    // Endpoint técnico /subjects
    axios.get('http://localhost:8080/subjects')
      .then(resposta => setMaterias(resposta.data))
      .catch(erro => console.error("Erro ao buscar matérias:", erro))
  }, [])

  const salvarMateria = (e) => {
    e.preventDefault()

    const novaMateriaJson = {
      name: nomeMateria,
      user: { id: parseInt(idUsuario) }
    }

    axios.post('http://localhost:8080/subjects', novaMateriaJson)
      .then(resposta => {
        setMaterias([...materias, resposta.data])
        setNomeMateria('')
        alert("Matéria criada com sucesso! 📚")
      })
      .catch(erro => console.error("Erro ao salvar matéria:", erro))
  }

  return (
    <div>
      <h1>Minhas Matérias 📚</h1>
      
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid black' }}>
        <h2>Cadastrar Nova Matéria</h2>
        <form onSubmit={salvarMateria}>
          <input type="number" placeholder="ID Usuário" value={idUsuario} onChange={(e) => setIdUsuario(e.target.value)} required />
          <input type="text" placeholder="Nome da Matéria" value={nomeMateria} onChange={(e) => setNomeMateria(e.target.value)} required />
          <button type="submit">Cadastrar Matéria</button>
        </form>
      </div>

      <ul>
        {materias.map(materia => (
          <li key={materia.id}>{materia.name}</li>
        ))}
      </ul>
    </div>
  )
}

export default Materias