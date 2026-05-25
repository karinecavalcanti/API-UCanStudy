import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import Usuarios from './pages/Usuarios'
import Estudos from './pages/Estudos'
import Metas from './pages/Metas'
import Materias from './pages/Materias'

function App() {
  return (
    <BrowserRouter>
      {/* --- MENU DE NAVEGAÇÃO SUPERIOR --- */}
      <nav style={{ padding: '20px', backgroundColor: '#eee', marginBottom: '20px' }}>
        <Link to="/usuarios" style={{ marginRight: '15px' }}>Usuários</Link>
        <Link to="/estudos" style={{ marginRight: '15px' }}>Sessões de Estudo</Link>
                <Link to="/materias" style={{ marginRight: '15px' }}>Matérias</Link>
        <Link to="/metas">Minhas Metas</Link>
      </nav>

      {/* --- O GERENTE DE PÁGINAS --- */}
      <div style={{ padding: '20px' }}>
        <Routes>
          <Route path="/usuarios" element={<Usuarios />} />
          <Route path="/estudos" element={<Estudos />} />
          <Route path="/metas" element={<Metas />} />
          <Route path="/materias" element={<Materias />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App