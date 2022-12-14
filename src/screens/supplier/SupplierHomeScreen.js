import Navigation from "../../components/Navigation";
import Footer from "../../components/Footer"; 
import React, { Component } from 'react'

class SupplierHomeScreen extends Component {
    constructor(props) {
        super(props)
        
        this.showProfile = this.showProfile.bind(this);
        this.editProfile = this.editProfile.bind(this);
        this.changePassword = this.changePassword.bind(this);
        this.changeAddress = this.changeAddress.bind(this);
        this.addProduct = this.addProduct.bind(this);
        this.productDetails = this.productDetails.bind(this);
        this.logout = this.logout.bind(this);
    }

    showProfile(){
        this.props.history.push('/myaccount/profile');
    }

    editProfile(){
        this.props.history.push('/myaccount/editprofile');
    }

    changePassword(){
        this.props.history.push('/myaccount/change-password');
    }

    changeAddress(){
        this.props.history.push('/myaccount/changeaddress');
    }

    addProduct(){
        this.props.history.push('/addproduct');
    }

    productDetails(){
        this.props.history.push('/supplier/showproducts');
    }

    logout(){
        this.props.history.push('/logout');
    }
   render(){
    return (
        <div>
            <Navigation/>
            <div className="main1">
            <table>
                <td><button className="btn btn-success btn-lg" onClick={() => this.showProfile()}>Profile</button></td>
                <td><button className="btn-warning" onClick={() => this.editProfile()}>Edit Profile</button></td>
                <td><button className="btn btn-success" onClick={() => this.changePassword()}>Change Password</button></td>
                <td><button className="btn-warning" onClick={() => this.changeAddress()}>Change Address</button></td>
                <td><button className="btn btn-success" onClick={() => this.addProduct()}>Add Product</button></td>
                <td><button className="btn-warning" onClick={() => this.productDetails()}>Product List</button></td>
                <td><button className="btn btn-success" onClick={() => this.logout()}>Logout</button></td>
            </table>
        </div>
        </div>
    );
   }
}
export default SupplierHomeScreen