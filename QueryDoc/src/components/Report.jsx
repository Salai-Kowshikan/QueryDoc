import React from "react";
import { FaDownload } from "react-icons/fa";
import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

function Report() {
  const navigate = useNavigate();
  return (
    <>
      <div>Report</div>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec
        nisl ultricies Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Sed ac nunc nec nisl ultricies
      </p>
      <div className="flex w-full justify-center space-x-4">
        <Button
          onClick={() => navigate("/crud")}
          className="bg-white text-black hover:bg-black hover:text-white hover:border-2 hover:border-white font-bold py-2 px-4 rounded transition duration-300"
        >
          CRUD Chart
        </Button>

        <Button onClick={() => navigate("/overall")} className="bg-black text-white border-2 border-white hover:bg-white hover:text-black font-bold py-2 px-4 rounded flex items-center transition duration-300">
          Overall Chart
        </Button>
      </div>
    </>
  );
}

export default Report;
