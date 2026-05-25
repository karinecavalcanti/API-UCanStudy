import { useState, useEffect } from 'react'
import axios from 'axios'

function Usuarios() {
  const [usuarios, setUsuarios] = useState([])
  const [nome, setNome] = useState('')
  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  
  // Estados para as novas funcionalidades
  const [editandoId, setEditandoId] = useState(null)
  const [usuarioSelecionado, setUsuarioSelecionado] = useState(null)

  useEffect(() => {
    carregarUsuarios()
  }, [])

  const carregarUsuarios = () => {
    axios.get('http://localhost:8080/users')
      .then(resposta => setUsuarios(resposta.data))
      .catch(erro => console.error("Erro ao carregar:", erro))
  }

  // --- DELETE ---
  const deletarUsuario = (id) => {
    if (window.confirm("Tem certeza que deseja deletar este usuário?")) {
      axios.delete(`http://localhost:8080/users/${id}`)
        .then(() => carregarUsuarios())
        .catch(erro => alert("Erro ao deletar: Usuário pode ter registros vinculados!"))
    }
  }

  // --- CONSULTA DETALHADA ---
  const consultarUsuario = (id) => {
    console.log("Chamando URL:", `http://localhost:8080/users/${id}`);
    axios.get(`http://localhost:8080/users/${id}`)
         .then(resposta => setUsuarioSelecionado(resposta.data))
         .catch(erro => console.error("Erro ao consultar:", erro))
    }

  // --- PREPARAR EDIÇÃO ---
  const iniciarEdicao = (usuario) => {
    setEditandoId(usuario.id)
    setNome(usuario.name)
    setEmail(usuario.email)
    setSenha(usuario.password)
  }

  // --- SAVE / UPDATE ---
  const salvarUsuario = (e) => {
    e.preventDefault()
    const usuarioJson = { name: nome, email: email, password: senha, level: 1, xp: 0 }

    if (editandoId) {
      axios.put(`http://localhost:8080/users/${editandoId}`, usuarioJson)
        .then(() => {
          setEditandoId(null)
          limparForm()
          carregarUsuarios()
        })
    } else {
      axios.post('http://localhost:8080/users', usuarioJson)
        .then(() => {
          limparForm()
          carregarUsuarios()
        })
    }
  }

  const limparForm = () => { setNome(''); setEmail(''); setSenha('') }

  return (
    <div>
      <h1>Gerenciar Usuários 👤</h1>
      
      <form onSubmit={salvarUsuario} style={{ marginBottom: '20px' }}>
        <input value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome" required />
        <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" required />
        <input value={senha} onChange={(e) => setSenha(e.target.value)} placeholder="Senha" type="password" required />
        <button type="submit">{editandoId ? "Atualizar" : "Cadastrar"}</button>
      </form>

      <ul>
        {usuarios.map(u => (
          <li key={u.id}>
            {u.name}
            <button onClick={() => consultarUsuario(u.id)} style={{ marginLeft: '10px' }}>Consultar</button>
            <button onClick={() => iniciarEdicao(u)} style={{ marginLeft: '5px' }}>Editar</button>
            <button onClick={() => deletarUsuario(u.id)} style={{ marginLeft: '5px', color: 'red' }}>Excluir</button>
          </li>
        ))}
      </ul>

      {/* --- ÁREA DE CONSULTA --- */}
      {usuarioSelecionado && (
        <div style={{ marginTop: '20px', padding: '15px', border: '2px solid blue' }}>
          <h3>Detalhes de {usuarioSelecionado.name}</h3>
          <p>Email: {usuarioSelecionado.email}</p>
          <p>Nível: {usuarioSelecionado.level}</p>
          <p>XP Total: {usuarioSelecionado.xp}</p>
          <button onClick={() => setUsuarioSelecionado(null)}>Fechar Consulta</button>
        </div>
      )}
    </div>
  )
}

export default Usuarios