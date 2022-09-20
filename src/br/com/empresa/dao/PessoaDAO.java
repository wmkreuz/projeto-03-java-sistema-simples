package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;

public class PessoaDAO implements IPessoaDAO {

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOValidationException, BOException {
		
		List<PessoaVO> pessoas = Dados.getPessoaVOs();
		List<PessoaVO> retorno = new ArrayList<PessoaVO>();
		
		for(PessoaVO pessoaVO : pessoas) {
			
			if(pessoaVO.getClienteVO().equals(cliente) == false) {
				continue;
			}
			
			if(tipoPessoa != null) {
				if(pessoaVO.getTippes() != null && 
						pessoaVO.getTippes().equals(tipoPessoa) == false) {
					continue;
				}
			}
			
			if(nomePessoa != null && nomePessoa.trim().length() > 0) {
				if(pessoaVO.getDescri().contains(nomePessoa) == false) {
					continue;
				}
			}
			
			if(cpfCnpj != null && cpfCnpj.replaceAll("\\.", "").replaceAll("/", "")
					.replaceAll("-", "").trim().length() > 0) {
				if(pessoaVO.getCpfcnpj().contains(cpfCnpj) == false) {
					continue;
				}
			}
			
			if(cidade != null && cidade.trim().length() > 0) {
				if(pessoaVO.getCidade() != null && 
						pessoaVO.getCidade().contains(cidade) == false) {
					continue;
				}
			}
			
			if(estado != null && estado.trim().length() > 0) {
				if(pessoaVO.getEstado() != null && 
						pessoaVO.getEstado().contains(estado) == false) {
					continue;
				}
			}
			
			retorno.add(pessoaVO);
			
		}
		
		return retorno;
	}

	@Override
	public void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		List<PessoaVO> pessoaVOs = Dados.getPessoaVOs();
		
		if(pessoa.getId() == null) {
			
			if(pessoaVOs.size() > 0) {
				PessoaVO ultimaPessoaVO = pessoaVOs.get(pessoaVOs.size() - 1);
				pessoa.setId(ultimaPessoaVO.getId().add(BigInteger.ONE));
			}else {
				pessoa.setId(BigInteger.ONE);
			}
			
			Dados.getPessoaVOs().add(pessoa);
			
		}else {
			
			for(int i = 0; i < pessoaVOs.size(); i++) {
				if(pessoaVOs.get(i).equals(pessoa)) {
					Dados.getPessoaVOs().set(i, pessoa);
				}
			}
			
		}
		
	}

	@Override
	public void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		for(int i = 0; i < Dados.getPessoaVOs().size(); i++) {
			if(Dados.getPessoaVOs().get(i).equals(pessoa)) {
				Dados.getPessoaVOs().remove(i);
			}
		}
		
	}

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException {

		for(int i = 0; i < Dados.getPessoaVOs().size(); i++) {
			if(Dados.getPessoaVOs().get(i).equals(pessoa)) {
				return Dados.getPessoaVOs().get(i);
			}
		}

		return null;
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
