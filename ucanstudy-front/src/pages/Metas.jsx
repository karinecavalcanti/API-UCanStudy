import { useState, useEffect } from 'react'
import axios from 'axios'

function Metas() {
  const [metas, setMetas] = useState([])
  
  // Memórias do formulário para /goals
  const [title, setTitle] = useState('')
  const [targetHours, setTargetHours] = useState('')
  const [progress, setProgress] = useState('')
  const [deadline, setDeadline] = useState('')
  const [idUsuario, setIdUsuario] = useState('')

  useEffect(() => {
    // Chamada ao endpoint técnico /goals
    axios.get('http://localhost:8080/goals')
      .then(resposta => setMetas(resposta.data))
      .catch(erro => console.error("Erro ao buscar metas:", erro))
  }, [])

  const salvarMeta = (e) => {
    e.preventDefault()

    const novaMetaJson = {
      title: title,
      target_hours: parseInt(targetHours),
      progress: parseInt(progress),
      deadline: deadline,
      completed: false, 
      user: { id: parseInt(idUsuario) }
    }

    axios.post('http://localhost:8080/goals', novaMetaJson)
      .then(resposta => {
        setMetas([...metas, resposta.data])
        setTitle('')
        setTargetHours('')
        setProgress('')
        setDeadline('')
        alert("Meta criada com sucesso! 🎯")
      })
      .catch(erro => console.error("Erro ao salvar meta:", erro))
  }

  return (
    <div>
      <h1>Minhas Metas 🎯</h1>
      
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid black' }}>
        <h2>Cadastrar Nova Meta</h2>
        <form onSubmit={salvarMeta}>
          <input type="number" placeholder="ID Usuário" value={idUsuario} onChange={(e) => setIdUsuario(e.target.value)} required />
          <input type="text" placeholder="Título" value={title} onChange={(e) => setTitle(e.target.value)} required />
          <input type="number" placeholder="Horas Alvo" value={targetHours} onChange={(e) => setTargetHours(e.target.value)} required />
          <input type="number" placeholder="Progresso" value={progress} onChange={(e) => setProgress(e.target.value)} required />
          <input type="date" value={deadline} onChange={(e) => setDeadline(e.target.value)} required />
          <button type="submit">Criar Meta</button>
        </form>
      </div>

      <ul>
        {metas.map(meta => (
          <li key={meta.id}>{meta.title} - Progresso: {meta.progress}h / {meta.target_hours}h</li>
        ))}
      </ul>
    </div>
  )
}

export default Metas