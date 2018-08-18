package com.atsjp.webDemo.service;

import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.dao.CompanyDao;
import com.atsjp.webDemo.dao.ContractDao;
import com.atsjp.webDemo.dao.CustomerDao;
import com.atsjp.webDemo.dao.Userdao;
import com.atsjp.webDemo.entity.Company;
import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.entity.User;

/*
 * 
 * �����servlet��װ������User�����customer����
 */
public class UserService {
	Userdao ud = new Userdao();
	CustomerDao cu = new CustomerDao();
	CompanyDao companyDao = new CompanyDao();
	ContractDao contractDao = new ContractDao();
	List<Customer> userlist = new LinkedList<Customer>();
	List<Company> companyList = new LinkedList<Company>();
	List<Contract> contractList = new LinkedList<Contract>();

	/*
	 * 
	 * ��֤����Ա��¼��Ϣ
	 */
	public boolean checkUser(User user) {
		User u = new User();
		u = ud.getUser(user); // ����servlet��װ�õ�user��Ϣ��ȥ���ݿ�ȡ����Ӧ��Ϣ��������ֵ������u������
		if (u != null) {
			if (user.getName().equals(u.getName())
					&& user.getPassword().equals(u.getPassword())) { // ȡ��servlet��װ�õ�user���������ݿ����ݺ˶�
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * 
	 * ����servlet���ص�Customer�������Customer�Ķ������ݿ���
	 */
	public boolean addCustomer(Customer customer) {
		if (cu.addCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * ɾ��cname��ӦCustomer����Ϣ
	 */
	public boolean deleteCustomer(Customer customer) {
		if (cu.deleteCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * ����servlet���ص�Customer����Ϣ����dao�����customer��Ϣ
	 */
	public boolean modifyCustomer(Customer customer) {
		if (cu.modifyCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * ����servlet���ص�page��pageSize������Customer��������ݿ��ҳ����
	 */
	public List<Customer> getAllCustomer(int page, int pageSize) {
		userlist = cu.page(page, pageSize);
		return userlist;
	}

	/*
	 * 
	 * ����servlet���ص�Customer��name����phoneֵ����dao����Ҳ��ҷ��ز�ѯ���
	 */
	public Customer getCustomer(Customer customer) {
		customer = cu.getCustomer(customer);
		return customer;
	}

	/*
	 * 
	 * ����servlet���ص�cname����Ϣ����dao��鿴customer��Ϣ�Ƿ����
	 */
	public boolean checkNameExist(String customerName) {
		Customer customer = new Customer();
		customer.setCname(customerName);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// �����Ѵ���
		} else {
			return false;// ����������
		}
	}

	/*
	 * 
	 * ����servlet���ص�cphone����Ϣ����dao��鿴customer��Ϣ�Ƿ����
	 */
	public boolean checkPhoneExist(String customerPhone) {
		Customer customer = new Customer();
		customer.setCphone(customerPhone);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// �ֻ������Ѵ���
		} else {
			return false;// �ֻ����벻����
		}
	}

	/*
	 *
	 * ����servlet���ص�page��pageSize������Company��������ݿ��ҳ����
	 */
	public List<Company> getAllCompany(int page, int pageSize) {
		companyList = companyDao.page(page, pageSize);
		return companyList;
	}

	/*
	 *
	 * ����servlet���ص�Company��name����phoneֵ����dao����Ҳ��ҷ��ز�ѯ���
	 */
	public Company getCompany(Company company) {
		company = companyDao.getCompany(company);
		return company;
	}

	/*
	 *
	 * ����servlet���ص�indexֵ����dao����Ҳ��ҷ��ز�ѯ���
	 */
	public List<Company> queryCompanyList(String index, int page, int pageSize){
	    List<Company> queryCompanyList = companyDao.queryCompanyList(index, page, pageSize);
	    return queryCompanyList;
    }

    /*
     *
     * ����servlet���ص�companyName����Ϣ����dao��鿴company��Ϣ�Ƿ����
     */
    public boolean checkCompanyNameExist(String companyName) {
        Company company = new Company();
        company.setCompanyname(companyName);
        Company tempc = companyDao.getCompany(company);
        if (tempc.getCompanyname() != null && tempc.getCompanyname() != "") {
            return true;// ��˾�����Ѵ���
        } else {
            return false;// ��˾���Ʋ�����
        }
    }

    /*
     *
     * ����servlet���ص�link����Ϣ����dao��鿴company��Ϣ�Ƿ����
     */
    public boolean checkLinkPhoneExist(String linkPhone) {
        Company company = new Company();
        company.setLinkphone(linkPhone);
        Company tempc = companyDao.getCompany(company);
        if (tempc.getCompanyname() != null && tempc.getCompanyname() != "") {
            return true;// ��ϵ�绰�Ѵ���
        } else {
            return false;// ��ϵ�绰������
        }
    }

    /*
	 *
	 * ����servlet���ص�Company�������Company�Ķ������ݿ���
	 */
    public boolean addCompany(Company company) {
        if (companyDao.addCompany(company)) {
            return true;
        } else {
            return false;
        }
    }

    /*
	 *
	 * ����servlet���ص�Company����Ϣ����dao�����company��Ϣ
	 */
    public boolean modifyCompany(Company company) {
        if (companyDao.modifyCompany(company)) {
            return true;
        } else {
            return false;
        }
    }

    /*
	 *
	 * ɾ��company��ӦCompany����Ϣ
	 */
    public boolean deleteCompany(Company company) {
        if (companyDao.deleteCompany(company)) {
            return true;
        } else {
            return false;
        }
    }

	/*
     *
     * ����servlet���ص�page��pageSize������Contract��������ݿ��ҳ����
     */
	public List<Contract> getAllContract(int page, int pageSize) {
		contractList = contractDao.page(page, pageSize);
		return contractList;
	}

	/*
	 *
	 * ����servlet���ص�Contract�������Contract�Ķ������ݿ���
	 */
	public boolean addContract(Contract contract) {
		if (contractDao.addContract(contract)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 *
	 * ɾ��contract��ӦContract����Ϣ
	 */
	public boolean deleteContract(Contract contract) {
		if (contractDao.deleteContract(contract)) {
			return true;
		} else {
			return false;
		}
	}

    /*
     *
     * ����servlet���ص�Contract��invoicenumberֵ����dao����Ҳ��ҷ��ز�ѯ���
     */
    public Contract getContract(Contract contract) {
        contract = contractDao.getContract(contract);
        return contract;
    }

    /*
	 *
	 * ����servlet���ص�Contract����Ϣ����dao�����contract��Ϣ
	 */
    public boolean modifyContract(Contract contract) {
        if (contractDao.modifyContract(contract)) {
            return true;
        } else {
            return false;
        }
    }

    /*
	 *
	 * ����servlet���ص�indexֵ����dao����Ҳ��ҷ��ز�ѯ���
	 */
    public List<Contract> queryContractList(String index, int page, int pageSize){
        List<Contract> queryContractList = contractDao.queryContractList(index, page, pageSize);
        return queryContractList;
    }

    /*
     *
     * ����servlet���ص�link����Ϣ����dao��鿴company��Ϣ�Ƿ����
     */
    public boolean checkInvoiceExist(String invoiceNumber) {
        Contract contract = new Contract();
        contract.setInvoicenumber(invoiceNumber);
        Contract tempc = contractDao.getContract(contract);
        if (tempc.getContractname() != null && tempc.getContractname() != "") {
            return true;// ��ϵ�绰�Ѵ���
        } else {
            return false;// ��ϵ�绰������
        }
    }

}
