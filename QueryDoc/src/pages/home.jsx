import React from "react";
import { Button } from "@/components/ui/button";
import { TypeAnimation } from "react-type-animation";
import Report from "@/components/Report";
import { FaDownload } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center bg-black text-white min-h-screen">
      {/* Landing Section */}
      <div className="flex flex-col items-center justify-center h-screen p-4">
        <h1 className="text-6xl font-bold mb-4">Query
            <span className="text-[#2196F3]">Doc</span></h1>
        <p className="text-lg mb-8 text-center max-w-xl">
          Dive into comprehensive database analysis with ease.
        </p>
        <div>Analyze databases like:</div>

        <div className="mb-8 text-2xl font-semibold text-[#2196F3]">
          <TypeAnimation
            sequence={[
              "PostgreSQL",
              1100,
              "MongoDB",
              1100,
              "Sharded MongoDB",
              1100,
              "CassandraDB",
              1100,
              "SELECT * FROM all_databases GROUP BY operation_type ORDER BY time ASC",
              2000,
            ]}
            wrapper="span"
            speed={50}
            style={{ display: "inline-block" , color: "#2196F3"}}
            repeat={Infinity}
          />
        </div>

        <div className="flex space-x-4">
          <a href="#report" className="flex items-center">
            <Button
              className="bg-white text-black hover:bg-black hover:text-white hover:border-2 hover:border-white font-bold py-2 px-4 rounded transition duration-300"
            >
              View Report
            </Button>
          </a>
          <Button
            className="bg-black text-white border-2 border-white hover:bg-white hover:text-black font-bold py-2 px-4 rounded flex items-center transition duration-300"
            onClick={() => navigate("/overall")}
          >
            <FaDownload className="mr-2" />
            Download Doc
          </Button>
        </div>
      </div>

      <div id="report" className="w-full py-20 px-4 bg-gray-900">
        <Report />
      </div>
    </div>
  );
}

export default Home;
