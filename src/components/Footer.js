import { Component } from 'react';
import {Link} from 'react-router-dom';

export default class Footer extends Component {
    constructor(props) {
        super(props)
        this.selectcategory = this.selectcategory.bind(this);
    }

    selectcategory(id, name) {
        window.localStorage.setItem("category_id", id);
        window.localStorage.setItem("category_name", name);
        this.props.history.push('/product-category');
    }

    render(){
        return (
        <div className="footer">
            <div  >
                <table width="90%" style={{marginLeft: "16px"}}>
                    <br></br>
                    <tr>
                    <td>
                    <tr>
                        <a href="/aboutus" className="nav-link">
                            <h6 className="nameColor">About Us</h6>
                        </a>
                    </tr>
                    <tr>
                        <a href="/contactus" className="nav-link">
                            <h6 className="nameColor">Contact Us</h6>
                        </a>
                    </tr>
                    <tr>
                        <a href="/termsnconditions" className="nav-link">
                            <h6 className="nameColor">Terms & Conditions</h6>
                        </a>
                    </tr>

                    <tr>
                        <a href="/privacypolicy" className="nav-link">
                            <h6 className="nameColor">Privacy Policy</h6>
                        </a>
                    </tr>

                    <tr>
                        <a href="/faqs" className="nav-link">
                            <h6 className="nameColor">FAQs</h6>
                        </a>
                    </tr>
                </td>

                <td className="float-end">
                    <div className="nameColor">
                        {' '}
                        <h2>Contact Us</h2> WhatsApp Us :{' '}
                        <span
                        style={{ display: 'inline-block;', marginBottom: '1rem;' }}>
                        <a>
                            +91 8805417509
                        </a>
                        <br></br>
                        <br></br>
                        <a>
                        <h6>Address:-</h6>
                        <h6>221 B , The Business Hub ,<br></br> Kothrud, Pune-411038</h6>
                        </a>
                        </span>
                        <br />
                        <br />
                        
                    </div>{' '}
                </td>
                </tr>
                </table>
                
                <div class="copyright">
                    <div>© 2022 jeevanshindegroup112, Fruit Basket </div>
                </div>
            </div>
            
        </div>
        )
    }
}