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
 * 处理从servlet封装过来的User对象和customer对象
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
	 * 验证管理员登录信息
	 */
	public boolean checkUser(User user) {
		User u = new User();
		u = ud.getUser(user); // 根据servlet封装好的user信息，去数据库取出对应信息，将返回值保存在u对象中
		if (u != null) {
			if (user.getName().equals(u.getName())
					&& user.getPassword().equals(u.getPassword())) { // 取出servlet封装好的user对象与数据库数据核对
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
	 * 根据servlet返回的Customer对象，添加Customer的对象到数据库中
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
	 * 删除cname对应Customer的信息
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
	 * 根据servlet返回的Customer的信息，到dao层更新customer信息
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
	 * 根据servlet返回的page、pageSize，进行Customer对象的数据库分页查找
	 */
	public List<Customer> getAllCustomer(int page, int pageSize) {
		userlist = cu.page(page, pageSize);
		return userlist;
	}

	/*
	 * 
	 * 根据servlet返回的Customer的name或者phone值，到dao层查找并且返回查询情况
	 */
	public Customer getCustomer(Customer customer) {
		customer = cu.getCustomer(customer);
		return customer;
	}

	/*
	 * 
	 * 根据servlet返回的cname的信息，到dao层查看customer信息是否存在
	 */
	public boolean checkNameExist(String customerName) {
		Customer customer = new Customer();
		customer.setCname(customerName);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// 姓名已存在
		} else {
			return false;// 姓名不存在
		}
	}

	/*
	 * 
	 * 根据servlet返回的cphone的信息，到dao层查看customer信息是否存在
	 */
	public boolean checkPhoneExist(String customerPhone) {
		Customer customer = new Customer();
		customer.setCphone(customerPhone);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// 手机号码已存在
		} else {
			return false;// 手机号码不存在
		}
	}

	/*
	 *
	 * 根据servlet返回的page、pageSize，进行Company对象的数据库分页查找
	 */
	public List<Company> getAllCompany(int page, int pageSize) {
		companyList = companyDao.page(page, pageSize);
		return companyList;
	}

	/*
	 *
	 * 根据servlet返回的Company的name或者phone值，到dao层查找并且返回查询情况
	 */
	public Company getCompany(Company company) {
		company = companyDao.getCompany(company);
		return company;
	}

	/*
	 *
	 * 根据servlet返回的index值，到dao层查找并且返回查询情况
	 */
	public List<Company> queryCompanyList(String index, int page, int pageSize){
	    List<Company> queryCompanyList = companyDao.queryCompanyList(index, page, pageSize);
	    return queryCompanyList;
    }

    /*
     *
     * 根据servlet返回的companyName的信息，到dao层查看company信息是否存在
     */
    public boolean checkCompanyNameExist(String companyName) {
        Company company = new Company();
        company.setCompanyname(companyName);
        Company tempc = companyDao.getCompany(company);
        if (tempc.getCompanyname() != null && tempc.getCompanyname() != "") {
            return true;// 公司名称已存在
        } else {
            return false;// 公司名称不存在
        }
    }

    /*
     *
     * 根据servlet返回的link的信息，到dao层查看company信息是否存在
     */
    public boolean checkLinkPhoneExist(String linkPhone) {
        Company company = new Company();
        company.setLinkphone(linkPhone);
        Company tempc = companyDao.getCompany(company);
        if (tempc.getCompanyname() != null && tempc.getCompanyname() != "") {
            return true;// 联系电话已存在
        } else {
            return false;// 联系电话不存在
        }
    }

    /*
	 *
	 * 根据servlet返回的Company对象，添加Company的对象到数据库中
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
	 * 根据servlet返回的Company的信息，到dao层更新company信息
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
	 * 删除company对应Company的信息
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
     * 根据servlet返回的page、pageSize，进行Contract对象的数据库分页查找
     */
	public List<Contract> getAllContract(int page, int pageSize) {
		contractList = contractDao.page(page, pageSize);
		return contractList;
	}

	/*
	 *
	 * 根据servlet返回的Contract对象，添加Contract的对象到数据库中
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
	 * 删除contract对应Contract的信息
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
     * 根据servlet返回的Contract的invoicenumber值，到dao层查找并且返回查询情况
     */
    public Contract getContract(Contract contract) {
        contract = contractDao.getContract(contract);
        return contract;
    }

    /*
	 *
	 * 根据servlet返回的Contract的信息，到dao层更新contract信息
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
	 * 根据servlet返回的index值，到dao层查找并且返回查询情况
	 */
    public List<Contract> queryContractList(String index, int page, int pageSize){
        List<Contract> queryContractList = contractDao.queryContractList(index, page, pageSize);
        return queryContractList;
    }

    /*
     *
     * 根据servlet返回的link的信息，到dao层查看company信息是否存在
     */
    public boolean checkInvoiceExist(String invoiceNumber) {
        Contract contract = new Contract();
        contract.setInvoicenumber(invoiceNumber);
        Contract tempc = contractDao.getContract(contract);
        if (tempc.getContractname() != null && tempc.getContractname() != "") {
            return true;// 联系电话已存在
        } else {
            return false;// 联系电话不存在
        }
    }

}
