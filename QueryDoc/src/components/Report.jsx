import React from "react";
import { FaDownload } from "react-icons/fa";
import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

function Report() {
  const navigate = useNavigate();
  return (
    <>
      <div className="text-3xl">Summary</div>
      <p className="font-sans text-lg pt-5">
        This dataset captures performance metrics for various database
        operations (Read, Create, Update, Delete) on different databases
        (PostgreSQL, MongoDB, Sharded MongoDB, and Cassandra). Each operation
        was conducted on 10,000 records (except some Delete and Update
        operations on 1 record). Cassandra shows higher times for most
        operations compared to others, especially on Create and Delete
        operations. PostgreSQL generally has lower times for both Read and
        Update operations, while MongoDB has varied times, with higher times in
        Update and Delete operations. Sharded MongoDB, although faster than
        Cassandra, is generally slower than PostgreSQL and MongoDB for these
        specific tasks. This data allows for evaluating each database's relative
        performance under identical workload conditions Visualize the execution
        time of the 4 dbs here....
      </p>
      <div className="flex w-full justify-center space-x-4">
        <Button
          onClick={() => navigate("/crud")}
          className="bg-white text-black hover:bg-black hover:text-white hover:border-2 hover:border-white font-bold py-2 px-4 rounded transition duration-300"
        >
          CRUD Chart
        </Button>

        <Button
          onClick={() => navigate("/overall")}
          className="bg-black text-white border-2 border-white hover:bg-white hover:text-black font-bold py-2 px-4 rounded flex items-center transition duration-300"
        >
          Overall Chart
        </Button>
      </div>
    </>
  );
}

export default Report;
