import { useState, useEffect } from 'react'
import axios from 'axios'

function Estudos() {
  const [sessoes, setSessoes] = useState([])
  
  // Memórias para o formulário
  const [dataEstudo, setDataEstudo] = useState('')
  const [duracao, setDuracao] = useState('')
  const [anotacoes, setAnotacoes] = useState('')
  const [idUsuario, setIdUsuario] = useState('')
  const [idMateria, setIdMateria] = useState('')

  // Busca as sessões quando a página carrega
  useEffect(() => {
    axios.get('http://localhost:8080/studysession')
      .then(resposta => setSessoes(resposta.data))
      .catch(erro => console.error("Erro ao buscar sessões:", erro))
  }, [])

  const salvarSessao = (e) => {
    e.preventDefault()

    // Montando o JSON exatamente como o Spring Boot espera
    const novaSessaoJson = {
      date: dataEstudo,
      duration: parseInt(duracao), // Converte texto para número
      notes: anotacoes,
      user: { id: parseInt(idUsuario) },
      subject: { id: parseInt(idMateria) }
    }

    axios.post('http://localhost:8080/studysession', novaSessaoJson)
      .then(resposta => {
        setSessoes([...sessoes, resposta.data])
        // Limpa as caixinhas de texto
        setDataEstudo('')
        setDuracao('')
        setAnotacoes('')
        alert("Sessão registrada! Você ganhou XP! 🚀")
      })
      .catch(erro => {
        console.error("Erro ao salvar:", erro)
        alert("Erro ao salvar a sessão.")
      })
  }

  return (
    <div>
      <h1>Minhas Sessões de Estudo 📚</h1>
      
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid black' }}>
        <h2>Registrar Novo Estudo</h2>
        <form onSubmit={salvarSessao}>
          <div>
            <label>ID do Usuário: </label>
            <input type="number" value={idUsuario} onChange={(e) => setIdUsuario(e.target.value)} required />
          </div>
          <br/>
          <div>
            <label>ID da Matéria: </label>
            <input type="number" value={idMateria} onChange={(e) => setIdMateria(e.target.value)} required />
          </div>
          <br/>
          <div>
            <label>Data: </label>
            <input type="date" value={dataEstudo} onChange={(e) => setDataEstudo(e.target.value)} required />
          </div>
          <br/>
          <div>
            <label>Duração (minutos): </label>
            <input type="number" value={duracao} onChange={(e) => setDuracao(e.target.value)} required />
          </div>
          <br/>
          <div>
            <label>Anotações: </label>
            <input type="text" value={anotacoes} onChange={(e) => setAnotacoes(e.target.value)} required />
          </div>
          <br/>
          <button type="submit">Salvar Sessão e Ganhar XP</button>
        </form>
      </div>

      <h2>Histórico de Estudos</h2>
      <ul>
        {sessoes.map(sessao => (
          <li key={sessao.id}>
            <strong>{sessao.date}</strong> - {sessao.duration} min (XP Ganho: {sessao.xp_earned})
            <br/>
            <em>Notas: {sessao.notes}</em>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default Estudos